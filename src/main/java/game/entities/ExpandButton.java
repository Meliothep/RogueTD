package game.entities;

import com.almasb.fxgl.entity.Entity;
import game.EntityType;
import javafx.geometry.Point3D;
import javafx.scene.shape.Box;

public class ExpandButton extends Entity {
    public ExpandButton(Point3D position) {
        setType(EntityType.EXPANDBUTTON);
        setPosition3D(position);
        setView();
    }

    private void setView() {
        Box crossx = new Box(0.2, 0.1, 0.8);
        Box crossy = new Box(0.8, 0.1, 0.2);
        getViewComponent().addChild(crossx);
        getViewComponent().addChild(crossy);
    }
}
