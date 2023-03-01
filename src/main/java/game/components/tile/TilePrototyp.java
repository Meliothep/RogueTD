package game.components.tile;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = TileJsonParser.class)
class TilePrototyp {
    private List<Point3D> wayCells = new ArrayList<>();

    public void add(Point3D point3D){
        wayCells.add(point3D);
    }

    public List<Point3D> getWayCells(){
        return wayCells;
    }
}
