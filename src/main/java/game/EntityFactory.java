package game;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import game.Utils.Directions;
import game.objects.tile.TileBuilder;

import java.io.IOException;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    @Spawns(value = "TILE")
    public Entity newCell(SpawnData data) throws IOException {
        return new TileBuilder().withEntryIn(Directions.NORTH)
                .addValidDirections(Directions.SOUTH)
                .setPosition(data.getX(), data.getY(), data.getZ())
                .build();
    }
}