package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import game.entities.ExpandButton;
import game.entities.Tower;
import game.entities.tile.Tile;
import game.utils.Direction;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;

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

    public static Tower spawnTower(Point3D position) {
        SpawnData data = new SpawnData(position);
        return (Tower) spawn("TOWER", data);
    }

    public static ExpandButton spawnExpandButton(Tile tile) {
        SpawnData data = new SpawnData();
        data.put("tile", tile);
        return (ExpandButton) spawn("EXPANDBUTTON", data);
    }

    public static void despawnEntity(Entity entity) {
        getGameWorld().removeEntity(entity);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
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
        getGameWorld().addEntityFactory(new GameEntityFactory());
        cameraSetup();
        getGameScene().setBackgroundColor(Color.valueOf("#7985ab"));
        Tile tile = spawnTile(new Point3D(0, 0, 2.8), Direction.SOUTH, new ArrayList<Direction>(List.of(Direction.NORTH, Direction.WEST, Direction.EAST)));
        spawn("BASE", 0, 0, 0);
        GameState.getInstance().addTileOrigin(new Point3D(0, 0, 0));
        GameState.getInstance().addTileOrigin(new Point3D(0, 0, 2.8));
        spawnExpandButton(tile);

    }

    protected void cameraSetup() {
        camera = getGameScene().getCamera3D();

        camera.getTransform().translateY(-10);
        camera.getTransform().translateX(8);
        camera.getTransform().lookAt(new Point3D(-10, 20, 0));
        camera.getTransform().translateZ(10);
    }
}