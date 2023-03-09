package game;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import game.Utils.Direction;
import game.exceptions.InvalidSpawnDataException;
import game.exceptions.TileBuilderException;
import game.objects.tile.TileBuilder;

import java.util.List;

public class EntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    @Spawns(value = "TILE")
    public Entity newCell(SpawnData data) throws InvalidSpawnDataException, TileBuilderException {
        if (!data.hasKey("entry") || data.get("entry") == null || !data.hasKey("validDirections") || !(data.get("validDirections") instanceof List<?>))
            throw new InvalidSpawnDataException("entry and validDirections must be set in SpawnData");

        return new TileBuilder().withEntryIn(data.get("entry"))
                .addValidDirections((List<Direction>) data.get("validDirections"))
                .setPosition(data.getX(), data.getY(), data.getZ())
                .build();
    }

    @Spawns(value = "Base")
    public Entity newBase(SpawnData data) throws InvalidSpawnDataException, TileBuilderException {
        if (!data.hasKey("entry") || data.get("entry") == null || !data.hasKey("validDirections") || !(data.get("validDirections") instanceof List<?>))
            throw new InvalidSpawnDataException("entry and validDirections must be set in SpawnData");

        return new TileBuilder().withEntryIn(Direction.EAST)
                .addValidDirections(Direction.WEST)
                .setPosition(data.getX(), data.getY(), data.getZ())
                .build();

    }
}