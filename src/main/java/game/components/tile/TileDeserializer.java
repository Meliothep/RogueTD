package game.components.tile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import game.components.tile.cell.FreeCell;
import game.components.tile.cell.WayCell;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileDeserializer extends StdDeserializer<Tile> {
    protected TileDeserializer() {
        super(Tile.class);
    }

    @Override
    public Tile deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Tile tile = new Tile();
        Random rn = new Random();

        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        JsonNode wayCells = root.path("wayCells");
        if(wayCells.isArray()){
            List<Point3D> wayCellsPoints = new ArrayList<>();
            for(JsonNode node: wayCells){
                JsonNode origin = node.path("origin");
                Point3D point3D = new Point3D(origin.path("x").asDouble(), 0, origin.path("z").asDouble());
                wayCellsPoints.add(point3D);
                tile.addWayCell(new WayCell(point3D));
            }
            for (int i = 0; i <= 6; i++) {
                double x = round(i * 0.4,1);
                for (int j = 0; j <= 6 ; j++) {
                    double z = round(j * 0.4,1);
                    Point3D currentPoint = new Point3D(x, 0 , z);
                    if (!wayCellsPoints.contains(currentPoint)){
                        int multiplier = 1 + (rn.nextInt(3) == 0?1:0);
                        multiplier = (multiplier == 2) ? multiplier + (rn.nextInt(5) == 0?1:0) : multiplier;
                        tile.addFreeCell(new FreeCell(currentPoint,multiplier));
                    }
                }
            }
        }
        return tile;
    }
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
