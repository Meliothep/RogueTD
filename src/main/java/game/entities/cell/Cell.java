package game.entities.cell;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.shape.Box;

public abstract class Cell {
    private final Point3D origin;

    protected Box box;

    protected Cell(Point3D origin, Box box) {
        this.origin = origin;
        this.box = box;
        box.setTranslateX(origin.getX());
        box.setTranslateZ(origin.getZ());
    }

    public Point3D getOrigin() {
        return origin;
    }

    public Node getBox() {
        return box;
    }
}