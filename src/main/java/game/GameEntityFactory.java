package game;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import game.entities.Base;
import game.entities.ExpandButton;
import game.entities.Tower;
import game.entities.tile.Tile;
import game.entities.tile.TileBuilder;
import game.exceptions.InvalidSpawnDataException;
import game.exceptions.TileBuilderException;
import game.utils.Direction;
import javafx.geometry.Point3D;

import java.util.List;

public class GameEntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    private static final String entryField = "entry";
    private static final String validDirectionsField = "validDirections";
    private static final String tileField = "tile";

    @Spawns(value = "TILE")
    public Entity newCell(SpawnData data) throws InvalidSpawnDataException, TileBuilderException {
        if (!data.hasKey(entryField) || data.get(entryField) == null)
            throw new InvalidSpawnDataException("entry must be set in SpawnData");
        if (!data.hasKey(validDirectionsField) || !(data.get(validDirectionsField) instanceof List<?>))
            throw new InvalidSpawnDataException("entry validDirections must be set in SpawnData");

        return new TileBuilder().withEntryIn(data.get(entryField))
                .addValidDirections((List<Direction>) data.get(validDirectionsField))
                .setPosition(data.getX(), data.getY(), data.getZ())
                .build();
    }

    @Spawns(value = "BASE")
    public Entity newBase(SpawnData data) {
        return new Base(new Point3D(data.getX(), data.getY(), data.getZ()));
    }

    @Spawns(value = "EXPANDBUTTON")
    public Entity newButton(SpawnData data) throws InvalidSpawnDataException {
        if (!data.hasKey(tileField) || data.get(tileField) == null || !(data.get(tileField) instanceof Tile))
            throw new InvalidSpawnDataException("entry must be set in SpawnData");
        return ExpandButton.fromTile(data.get(tileField));
    }

    @Spawns(value = "TOWER")
    public Entity newTower(SpawnData data) {
        return new Tower(new Point3D(data.getX(), data.getY(), data.getZ()));
    }
}