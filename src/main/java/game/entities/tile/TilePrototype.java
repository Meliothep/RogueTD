package game.entities.tile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import game.objects.cell.FreeCell;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.utils.Utils.rotatePoint;
import static game.utils.Utils.round;

@JsonDeserialize(using = TileJsonParser.class)
class TilePrototype {
    private List<Point3D> wayCells = new ArrayList<>();

    public static TilePrototype fromJson(String jsonName) throws IOException {

        InputStream json = TilePrototype.class.getClassLoader().getResourceAsStream("assets/tiles/" + jsonName + ".json");
        return new ObjectMapper().readValue(json, TilePrototype.class);
    }

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
                }
            }
        }
        return tile;
    }

    public void rotate(double angle) {
        List<Point3D> result = new ArrayList<>();
        for (Point3D point3D : wayCells) {
            result.add(rotatePoint(point3D, angle));
        }
        wayCells = result;
    }
}
