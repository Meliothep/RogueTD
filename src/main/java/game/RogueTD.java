package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.multiplayer.MultiplayerService;
import com.almasb.fxgl.net.Connection;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 *
 *
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
        var cameraSpeed = 0.25;

        onKey(KeyCode.Z, () -> {
            camera.getTransform().translateZ(cameraSpeed);
            camera.getTransform().translateX(-cameraSpeed);
        });
        onKey(KeyCode.S, () -> {
            camera.getTransform().translateZ(-cameraSpeed);
            camera.getTransform().translateX(cameraSpeed);
        });
        onKey(KeyCode.Q, () -> {
            camera.getTransform().translateX(-cameraSpeed);
            camera.getTransform().translateZ(-cameraSpeed);
        });
        onKey(KeyCode.D, () -> {
            camera.getTransform().translateX(cameraSpeed);
            camera.getTransform().translateZ(cameraSpeed);
        });
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new EntityFactory());
        cameraSetup();
        Entity cell = spawn("TILE", new Point3D(0, 0, 0));
        System.out.println("cell : " + cell.getPosition3D());
    }

    protected void cameraSetup(){
        camera = getGameScene().getCamera3D();

        camera.getTransform().translateY(-8);
        camera.getTransform().translateX(8);
        camera.getTransform().lookAt(new Point3D(-10,5,0));
        camera.getTransform().translateZ(10);
        System.out.println("cam : " +camera.getTransform().getPosition3D());
    }
}