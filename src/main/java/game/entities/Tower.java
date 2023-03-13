package game.entities;

import com.almasb.fxgl.entity.Entity;
import game.EntityType;
import game.eventhandlers.FilledCellClickHandler;
import javafx.geometry.Point3D;
import javafx.scene.shape.Cylinder;

public class Tower extends Entity {

    private Cylinder cylinder;

    public Tower(Point3D position) {
        setType(EntityType.TOWER);
        setPosition3D(position);
        setView();
    }

    private void setView() {
        cylinder = new Cylinder(0.1, 0.3);
        getViewComponent().addChild(cylinder);
    }

    public void setHandler(FilledCellClickHandler handler) {
        this.cylinder.setOnMousePressed(handler);
    }
}
