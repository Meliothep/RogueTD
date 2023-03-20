package game.entities.tile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

class TileJsonParser extends StdDeserializer<TilePrototype> {
    protected TileJsonParser() {
        super(Tile.class);
    }

    @Override
    public TilePrototype deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        JsonNode wayCells = root.path("wayCells");
        JsonNode wayPoints = root.path("wayPoints");
        if (!wayCells.isArray() || !wayPoints.isArray())
            throw new InvalidPropertiesFormatException("Le json ne contient pas de tableau 'wayCells' ou 'wayPoints'");

        TilePrototype tilePrototyp = new TilePrototype();

        for (JsonNode node : wayCells) {
            JsonNode origin = node.path("origin");
            Point3D point3D = new Point3D(origin.path("x").asDouble(), 0, origin.path("z").asDouble());
            tilePrototyp.getWayCells().add(point3D);
        }
        for (JsonNode node : wayPoints) {
            JsonNode origin = node.path("origin");
            Point3D point3D = new Point3D(origin.path("x").asDouble(), -.3, origin.path("z").asDouble());
            tilePrototyp.getWayPoints().add(point3D);
        }

        return tilePrototyp;
    }
}
