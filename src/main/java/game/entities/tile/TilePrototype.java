package game.entities.tile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import game.entities.cell.FreeCell;
import game.entities.cell.WayCell;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.utils.Utils.round;

@JsonDeserialize(using = TileJsonParser.class)
class TilePrototype {
    private List<Point3D> wayCells = new ArrayList<>();

    public List<Point3D> getWayCells() {
        return wayCells;
    }

    protected void setWayCells(List<Point3D> wayCells) {
        this.wayCells = wayCells;
    }

    public Tile getTile() {
        Tile tile = new Tile();
        Random rn = new Random();

        for (int i = 0; i <= 6; i++) {
            double x = round(i * 0.4, 1);
            for (int j = 0; j <= 6; j++) {
                double z = round(j * 0.4, 1);
                Point3D currentPoint = new Point3D(x, 0, z);
                if (!wayCells.contains(currentPoint)) {
                    int multiplier = 1 + (rn.nextInt(3) == 0 ? 1 : 0);
                    multiplier = (multiplier == 2) ? multiplier + (rn.nextInt(5) == 0 ? 1 : 0) : multiplier;
                    tile.addFreeCell(new FreeCell(currentPoint, multiplier));
                } else {
                    tile.addWayCell(new WayCell(currentPoint));
                }
            }
        }
        return tile;
    }

    public static TilePrototype fromJson(String jsonName) throws IOException {

        InputStream json = TilePrototype.class.getClassLoader().getResourceAsStream("assets/tiles/" + jsonName + ".json");
        return new ObjectMapper().readValue(json, TilePrototype.class);
    }

    public void rotate(double angle) {
        List<Point3D> result = new ArrayList<>();
        for (Point3D point3D : wayCells) {
            double s = Math.sin(Math.toRadians(angle));
            double c = Math.cos(Math.toRadians(angle));

            // translate point back to origin:
            double currentX = point3D.getX() - 1.2;
            double currentZ = point3D.getZ() - 1.2;

            // rotate point
            double xnew = currentX * c - currentZ * s;
            double ynew = currentX * s + currentZ * c;
            result.add(new Point3D(round(xnew + 1.2, 1), 0, round(ynew + 1.2, 1)));
        }
        wayCells = result;
    }
}
