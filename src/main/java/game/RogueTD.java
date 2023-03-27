package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import game.UI.CardSelectionPane;
import game.UI.TopInfoPane;
import game.UI.TowerDetailPane;
import game.components.EnemyComponent;
import game.entities.tile.Base;
import game.exceptions.CantSpawnButtonException;
import game.utils.observer.ObservableTowerData;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static game.SpawnHelper.spawnExpandButton;
import static game.SpawnHelper.spawnWave;
import static game.datas.Config.*;
import static game.datas.Vars.*;

public class RogueTD extends GameApplication {
    private Camera3D camera;
    private TowerDetailPane towerStats;
    private CardSelectionPane upgradeSelectionPane;

    public static void main(String[] args) {
        launch(args);
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
        } catch (CantSpawnButtonException ignored) {
        }
    }

    private void cameraSetup() {
        camera = getGameScene().getCamera3D();

        camera.getTransform().translateY(-10);
        camera.getTransform().translateX(8);
        camera.getTransform().lookAt(new Point3D(-10, 20, 0));
        camera.getTransform().translateZ(10);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(NUM_ENEMIES, 0);
        vars.put(MONEY, STARTING_MONEY);
        vars.put(PLAYER_HP, STARTING_HP);
        vars.put(CURRENT_WAVE, 0);
        vars.put(TOWER_COST, 100);
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

    public void onTowerClick(ObservableTowerData data) {
        if (towerStats.isVisible() && data == towerStats.getData()) {
            hideTowerStat();
        } else {
            towerStats.setVisible(true);
            towerStats.setData(data);
            towerStats.setTranslateX(FXGL.getInput().mouseXUIProperty().get());
            towerStats.setTranslateY(FXGL.getInput().mouseYUIProperty().get());
        }

    }

    public void onExpand() {
        spawnWave();
    }


    public void onUpgradeSelected() {
        upgradeSelectionPane.setVisible(false);
        upgradeSelectionPane.setTranslateX(-SCREEN_W);

        try {
            spawnExpandButton(GameState.getInstance().getLastTile());
        } catch (CantSpawnButtonException ignored) {
            spawnWave();
        }
    }

    private void gameOver() {
    }

    private void hideTowerStat() {
        towerStats.setVisible(false);
        towerStats.setTranslateX(-150);
    }
}