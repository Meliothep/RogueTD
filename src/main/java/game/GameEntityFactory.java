package game;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import game.exceptions.InvalidSpawnDataException;
import game.exceptions.TileBuilderException;
import game.objects.tile.TileBuilder;
import game.utils.Direction;

import java.util.List;

public class GameEntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    private static final String entryField = "entry";
    private static final String validDirectionsField = "validDirections";

    @Spawns(value = "TILE")
    public Entity newCell(SpawnData data) throws InvalidSpawnDataException, TileBuilderException {
        if (!data.hasKey(entryField) || data.get(entryField) == null || !data.hasKey(validDirectionsField) || !(data.get(validDirectionsField) instanceof List<?>))
            throw new InvalidSpawnDataException("entry and validDirections must be set in SpawnData");

        return new TileBuilder().withEntryIn(data.get(entryField))
                .addValidDirections((List<Direction>) data.get(validDirectionsField))
                .setPosition(data.getX(), data.getY(), data.getZ())
                .build();
    }

    @Spawns(value = "Base")
    public Entity newBase(SpawnData data) throws InvalidSpawnDataException, TileBuilderException {
        if (!data.hasKey(entryField) || data.get(entryField) == null || !data.hasKey(validDirectionsField) || !(data.get(validDirectionsField) instanceof List<?>))
            throw new InvalidSpawnDataException("entry and validDirections must be set in SpawnData");

        return new TileBuilder().withEntryIn(Direction.EAST)
                .addValidDirections(Direction.WEST)
                .setPosition(data.getX(), data.getY(), data.getZ())
                .build();

    }
}