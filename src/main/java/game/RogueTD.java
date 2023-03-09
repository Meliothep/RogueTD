package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import game.utils.Direction;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class RogueTD extends GameApplication {
    private Camera3D camera;

    public static void main(String[] args) {
        launch(args);
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
        getGameWorld().addEntityFactory(new RogueTDEntityFactory());
        cameraSetup();

        SpawnData data = new SpawnData(new Point3D(0, 0, 0));
        data.put("entry", Direction.SOUTH);
        data.put("validDirections", new ArrayList<Direction>(List.of(Direction.NORTH, Direction.WEST, Direction.EAST)));
        spawn("TILE", data);

    }

    protected void cameraSetup() {
        camera = getGameScene().getCamera3D();

        camera.getTransform().translateY(-10);
        camera.getTransform().translateX(8);
        camera.getTransform().lookAt(new Point3D(-10, 20, 0));
        camera.getTransform().translateZ(10);
    }
}