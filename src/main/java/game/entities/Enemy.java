package game.entities;

import com.almasb.fxgl.entity.Entity;
import game.EntityType;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class Enemy extends Entity {

    private Sphere sphere;

    public Enemy(Point3D position) {
        setType(EntityType.TOWER);
        setPosition3D(position);
        setView();
    }

    private void setView() {
        sphere = new Sphere(0.2);
        sphere.setMaterial(new PhongMaterial(Color.valueOf("#FF0000")));
        getViewComponent().addChild(sphere);
    }
}
