package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import game.components.EnemyComponent;
import game.datas.EnemyData;
import game.datas.WaveData;
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
        gameSettings.setWidth(1280);
        gameSettings.setHeight(720);
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
                //gameOver();
            }
        });

        getWorldProperties().<Integer>addListener(CURRENT_WAVE, (old, newValue) -> {
            //Animations.playWaveIconAnimation(waveIcon);
        });

        getWorldProperties().<Integer>addListener(MONEY, (old, newValue) -> {
            if (newValue > MAX_MONEY) {
                set(MONEY, MAX_MONEY);
            }
        });
    }

    @Override
    protected void initUI() {
        /*
        FXGL.addUINode(new TopInfoPane());

        detailPane = new TowerDetailPane();
        FXGL.addUINode(detailPane);
        hideDetailPane();
        */
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(NUM_ENEMIES, 0);
        vars.put(MONEY, STARTING_MONEY);
        vars.put(PLAYER_HP, STARTING_HP);
        vars.put(CURRENT_WAVE, 0);
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
        try {
            spawnExpandButton(GameState.getInstance().getLastTile());
        } catch (Exception ignored) {
        }
    }

    public void onExpand() {
        inc(CURRENT_WAVE, 1);
        var wdata = new WaveData(geti(CURRENT_WAVE));

        EnemyData edata = new EnemyData(
                geti(CURRENT_WAVE) * 5,
                geti(CURRENT_WAVE) * 10,
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
}