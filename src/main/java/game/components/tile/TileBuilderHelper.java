package game.components.tile;

import game.components.tile.cell.FreeCell;
import game.components.tile.cell.WayCell;
import javafx.geometry.Point3D;

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
    private static List<Point3D> rotateWaycells(List<Point3D> waycellPoints, double angle){
        return null;
    }
}
