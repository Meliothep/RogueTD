package game.entities.cell;

import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class FreeCell extends Cell {
    int multiplier;
    private Point3D origin;
    private boolean hasTower;

    public FreeCell(Point3D origin, int multiplier) {
        super(origin, new Box(0.4, 0.4 + 0.2 * (multiplier - 1), 0.4));
        box.setTranslateY(-(0.4 + 0.2 * (multiplier - 1)) / 2);
        box.setMaterial(new PhongMaterial(Color.valueOf("#65cd50")));
        this.multiplier = multiplier;
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
