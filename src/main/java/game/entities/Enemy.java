package game.entities;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import game.EntityType;
import game.entities.tile.Tile;
import game.eventhandlers.FilledCellClickHandler;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class Enemy extends Entity {
    private Sphere sphere;

    public Enemy(Point3D position) {
        setType(EntityType.ENEMY);
        setPosition3D(position);
        setView();
    }

    public static Point3D fromTile(Tile tile) {
        Point3D spawnCoord = new Point3D(0, 0, 0);
        if (tile.getDirection() == null)
            return new Point3D(tile.getPosition3D().getX() + 1.4, -0.5, tile.getPosition3D().getZ() + 1.4);

        switch (tile.getDirection()) {
            case EAST -> {
                spawnCoord = new Point3D(tile.getPosition3D().getX() + 3.2, -0.5, tile.getPosition3D().getZ() + 1.2);
            }
            case WEST -> {
                spawnCoord = new Point3D(tile.getPosition3D().getX() - 0.8, -0.5, tile.getPosition3D().getZ() + 1.2);
            }
            case NORTH -> {
                spawnCoord = new Point3D(tile.getPosition3D().getX() + 1.2, -0.5, tile.getPosition3D().getZ() + 3.2);
            }
            case SOUTH -> {
                spawnCoord = new Point3D(tile.getPosition3D().getX() + 1.2, -0.5, tile.getPosition3D().getZ() - 0.8);
            }
        }
        return spawnCoord;
    }


    private void setView() {
        sphere = new Sphere(0.1);
        sphere.setMaterial(new PhongMaterial(Color.valueOf("#FF0000")));
        getViewComponent().addChild(sphere);
        getBoundingBoxComponent().addHitBox(new HitBox("__VIEW__", BoundingShape.Companion.box(0.2, 0.2)));
    }

    public void setHandler(FilledCellClickHandler handler) {
        this.sphere.setOnMousePressed(handler);
    }
}
