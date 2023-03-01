package game;

import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.components.tile.Tile;
import game.components.tile.TileBuilderHelper;
import game.components.tile.cell.Cell;

import java.io.IOException;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    @Spawns(value = "TILE")
    public Entity newCell(SpawnData data) throws IOException {

        var truc = TileBuilderHelper.getWaycellsFromJson("tile");

        Tile tile = TileBuilderHelper.fromWaycellsOrigins( TileBuilderHelper.rotateWaycells(truc, Math.toRadians(90)));
        EntityBuilder entityBuilder = entityBuilder(data)
                .type(EntityType.TILE)
                .at(data.getX(), data.getY(), data.getZ())
                .bbox(new HitBox(BoundingShape.box3D(10, 1.2, 10)));
        for (Cell cell : tile.getCells()){
            entityBuilder.view(cell.getbox());
        }

        return entityBuilder.build();
    }
}