package game.components.tile.cell;

import com.fasterxml.jackson.annotation.JsonRootName;
import game.components.tile.cell.Cell;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
@JsonRootName(value = "WayCell")
public class WayCell extends Cell {

    public WayCell(Point3D origin) {
        super(origin,new Box(0.4, 0.2,0.4 ));
        box.setTranslateY(-(0.2)/2);
        box.setMaterial(new PhongMaterial(Color.valueOf("#feffb1")));
    }

}
