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
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Random;

class TileJsonParser extends StdDeserializer<TilePrototyp> {
    protected TileJsonParser() {
        super(Tile.class);
    }
    @Override
    public TilePrototyp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        JsonNode wayCells = root.path("wayCells");

        if(!wayCells.isArray())
            throw new InvalidPropertiesFormatException("Le json ne contient pas de tableau 'wayCells'");

        TilePrototyp tilePrototyp = new TilePrototyp();

        for(JsonNode node: wayCells){
            JsonNode origin = node.path("origin");
            Point3D point3D = new Point3D(origin.path("x").asDouble(), 0, origin.path("z").asDouble());
            tilePrototyp.add(point3D);
        }


        return tilePrototyp;
    }
}
