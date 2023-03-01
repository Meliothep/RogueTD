package game.components.tile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import game.components.tile.cell.FreeCell;
import game.components.tile.cell.WayCell;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static game.Utils.Utils.round;

public class TileBuilderHelper {
    public static Tile fromWaycellsOrigins(List<Point3D> waycellPoints){
        Tile tile = new Tile();
        Random rn = new Random();

        for (int i = 0; i <= 6; i++) {
            double x = round(i * 0.4,1);
            for (int j = 0; j <= 6 ; j++) {
                double z = round(j * 0.4,1);
                Point3D currentPoint = new Point3D(x, 0 , z);
                if (!waycellPoints.contains(currentPoint)){
                    int multiplier = 1 + (rn.nextInt(3) == 0?1:0);
                    multiplier = (multiplier == 2) ? multiplier + (rn.nextInt(5) == 0?1:0) : multiplier;
                    tile.addFreeCell(new FreeCell(currentPoint,multiplier));
                }else{
                    tile.addWayCell(new WayCell(currentPoint));
                }
            }
        }
        return tile;
    }

    public static List<Point3D> getWaycellsFromJson(String jsonName) throws IOException {

        InputStream json = TileBuilderHelper.class.getClassLoader().getResourceAsStream("assets/tiles/"+ jsonName +".json");
        return new ObjectMapper().readValue(json,TilePrototyp.class).getWayCells();
    }

    public static List<Point3D> rotateWaycells(List<Point3D> waycellPoints, double angle){
        List<Point3D> result = new ArrayList<>();
        for (Point3D point3D : waycellPoints) {
            double s = Math.sin(angle);
            double c = Math.cos(angle);

            // translate point back to origin:
            double currentX = point3D.getX() - 1.2;
            double currentZ = point3D.getZ() - 1.2;

            // rotate point
            double xnew = currentX * c - currentZ * s;
            double ynew = currentX * s + currentZ * c;
            result.add(new Point3D(round(xnew + 1.2,1), 0, round(ynew + 1.2,1)));
        }
        return result;
    }
}
