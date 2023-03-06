package game.objects.tile.cell;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class WayCell extends Cell {

    public WayCell(Point3D origin) {
        super(origin,new Box(0.4, 0.2,0.4 ));
        box.setTranslateY(-(0.2)/2);
        box.setMaterial(new PhongMaterial(Color.valueOf("#feffb1")));
    }

}
