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

        var truc = TileBuilderHelper.getWaycellsFromJson("rightAngle");

        Tile tile = TileBuilderHelper.fromWaycellsOrigins( TileBuilderHelper.rotateWaycells(truc, Math.toRadians(-90)));

        tile.setType(EntityType.TILE);
        tile.setPosition3D(data.getX(), data.getY(), data.getZ());
        for (Cell cell : tile.getCells()){
            tile.getViewComponent().addChild(cell.getbox());
        }
        return tile;
    }
}