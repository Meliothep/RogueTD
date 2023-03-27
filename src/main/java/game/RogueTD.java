package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import game.UI.CardSelectionPane;
import game.UI.TopInfoPane;
import game.UI.TowerDetailPane;
import game.components.EnemyComponent;
import game.datas.EnemyData;
import game.datas.WaveData;
import game.datas.towerdatas.NormalTowerData;
import game.entities.ExpandButton;
import game.entities.Tower;
import game.entities.tile.Tile;
import game.entities.tile.monument.Base;
import game.utils.Direction;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static game.datas.Config.*;
import static game.datas.Vars.*;

public class RogueTD extends GameApplication {
    private Camera3D camera;

    private TowerDetailPane towerStats;
    private CardSelectionPane upgradeSelectionPane;

    public static void main(String[] args) {
        launch(args);
    }

    public static Tile spawnTile(Point3D position, Direction entry, List<Direction> directions) {
        SpawnData data = new SpawnData(position);
        data.put("entry", entry);
        data.put("validDirections", directions);

        return (Tile) spawn("TILE", data);
    }

    public static Tower spawnTower(Point3D position, int multiplier) {
        SpawnData data = new SpawnData(position);
        data.put("multiplier", multiplier);
        return (Tower) spawn("TOWER", data);
    }

    public static ExpandButton spawnExpandButton(Tile tile) throws Exception {
        SpawnData data = new SpawnData();
        data.put("tile", tile);
        if (tile.getDirection() == null) {
            throw new Exception();
        }
        return (ExpandButton) spawn("EXPANDBUTTON", data);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("Rogue TD");
        gameSettings.set3D(true);
        gameSettings.setWidth(SCREEN_W);
        gameSettings.setHeight(SCREEN_H);
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.Z, () -> camera.moveForwardXZ());
        onKey(KeyCode.S, () -> camera.moveBackXZ());
        onKey(KeyCode.Q, () -> camera.moveLeft());
        onKey(KeyCode.D, () -> camera.moveRight());

        FXGL.getInput().addEventHandler(ScrollEvent.SCROLL, scrollEvent -> {
            if (scrollEvent.getDeltaY() > 0) {
                camera.moveForward();
            } else {
                camera.moveBack();
            }
        });
    }

    @Override
    protected void initGame() {
        initVarListeners();

        getGameWorld().addEntityFactory(new GameEntityFactory());
        cameraSetup();
        getGameScene().setBackgroundColor(Color.valueOf("#7985ab"));

        GameState.getInstance().addTileOrigin(new Point3D(0, 0, 0));
        try {
            Base base = (Base) spawn("BASE", 0, 0, 0);
            spawnExpandButton(base);
            GameState.getInstance().initWays(base);
        } catch (Exception ignored) {
        }
    }

    private void initVarListeners() {
        getWorldProperties().<Integer>addListener(NUM_ENEMIES, (old, newValue) -> {
            if (newValue == 0) {
                onWaveEnd();
            }
        });

        getWorldProperties().<Integer>addListener(PLAYER_HP, (old, newValue) -> {
            if (newValue == 0) {
                gameOver();
            }
        });

        getWorldProperties().<Integer>addListener(MONEY, (old, newValue) -> {
            if (newValue > MAX_MONEY) {
                set(MONEY, MAX_MONEY);
            }
        });
    }

    @Override
    protected void initUI() {
        FXGL.addUINode(new TopInfoPane());
        towerStats = new TowerDetailPane();
        towerStats.translateXProperty().set(-150);
        towerStats.setVisible(false);
        FXGL.addUINode(towerStats);

        upgradeSelectionPane = new CardSelectionPane();
        upgradeSelectionPane.translateXProperty().set(-SCREEN_W);
        upgradeSelectionPane.setVisible(false);
        FXGL.addUINode(upgradeSelectionPane);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(NUM_ENEMIES, 0);
        vars.put(MONEY, STARTING_MONEY);
        vars.put(PLAYER_HP, STARTING_HP);
        vars.put(CURRENT_WAVE, 0);
        vars.put(TOWER_COST, 100);
    }

    protected void cameraSetup() {
        camera = getGameScene().getCamera3D();

        camera.getTransform().translateY(-10);
        camera.getTransform().translateX(8);
        camera.getTransform().lookAt(new Point3D(-10, 20, 0));
        camera.getTransform().translateZ(10);
    }

    public void onEnemyKilled(Entity enemy) {
        inc(MONEY, enemy.getComponent(EnemyComponent.class).getData().reward());
        inc(NUM_ENEMIES, -1);
    }

    public void onEnemyReachedEnd(Entity enemy) {
        inc(PLAYER_HP, -1);
        inc(NUM_ENEMIES, -1);
    }

    private void onWaveEnd() {
        if (geti(CURRENT_WAVE) % 3 == 0) {
            upgradeSelectionPane.setTranslateX(0);
            upgradeSelectionPane.changeValues();
            upgradeSelectionPane.setVisible(true);
        } else {
            onUpgradeSelected();
        }

    }

    public void onTowerClick(NormalTowerData data) {
        if (towerStats.isVisible() && data == towerStats.getData()) {
            hideTowerStat();
        } else {
            towerStats.setVisible(true);
            towerStats.setData(data);
            towerStats.setTranslateX(FXGL.getInput().mouseXUIProperty().get());
            towerStats.setTranslateY(FXGL.getInput().mouseYUIProperty().get());
        }

    }

    private void hideTowerStat() {
        towerStats.setVisible(false);
        towerStats.setTranslateX(-150);
    }

    public void onExpand() {
        spawnWave();
    }

    private void spawnWave() {
        inc(CURRENT_WAVE, 1);
        var wdata = new WaveData(geti(CURRENT_WAVE));

        EnemyData edata = new EnemyData(
                geti(CURRENT_WAVE) * 10,
                geti(CURRENT_WAVE) * 7,
                0.02,
                0.4);

        for (int i = 0; i < wdata.getEnemyCount(); i++) {
            runOnce(() -> {
                spawn("ENEMY",
                        new SpawnData()
                                .put("tile", GameState.getInstance().getLastTile())
                                .put("enemyData", edata)
                );

            }, Duration.seconds(i * edata.interval()));
        }

        inc(NUM_ENEMIES, wdata.getEnemyCount());
    }

    public void onUpgradeSelected() {
        upgradeSelectionPane.setVisible(false);
        upgradeSelectionPane.setTranslateX(-SCREEN_W);

        try {
            spawnExpandButton(GameState.getInstance().getLastTile());
        } catch (Exception ignored) {
            spawnWave();
        }
    }

    private void gameOver() {
    }

}