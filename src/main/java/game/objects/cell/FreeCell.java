package game.objects.cell;

import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class FreeCell {
    private final Point3D origin;
    protected Box box;
    int multiplier;
    private boolean hasTower;


    public FreeCell(Point3D origin, int multiplier) {
        this.origin = origin;
        this.box = new Box(0.4, 0.2 * multiplier, 0.4);
        box.setTranslateX(origin.getX());
        box.setTranslateZ(origin.getZ());
        box.setTranslateY((-(0.2 * multiplier) / 2) - 0.2);
        box.setMaterial(new PhongMaterial(Color.valueOf("#65cd50")));
        this.multiplier = multiplier;
    }

    public Point3D getOrigin() {
        return origin;
    }

    public Node getBox() {
        return box;
    }

    public void setHandler(EventHandler<MouseEvent> handler) {
        this.box.setOnMousePressed(handler);
    }

    public int getMultiplier() {
        return multiplier;
    }

    public boolean hasTower() {
        return hasTower;
    }

    public void setHasTower(boolean hasTower) {
        this.hasTower = hasTower;
    }
}
