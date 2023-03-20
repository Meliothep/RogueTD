package game.datas;


import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Way {

    private final List<Point3D> waypoints;

    public Way(List<Point3D> waypoints) {
        this.waypoints = waypoints;
    }

    public List<Point3D> getWaypoints() {
        return new ArrayList<>(waypoints);
    }
}