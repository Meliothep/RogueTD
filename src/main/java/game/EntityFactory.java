package game;

import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.components.tile.Tile;
import game.components.tile.cell.Cell;
import game.components.tile.cell.FreeCell;
import game.components.tile.cell.WayCell;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.io.File;
import java.io.IOException;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    @Spawns(value = "TILE")
    public Entity newCell(SpawnData data) throws IOException {
        Tile tile = new ObjectMapper().readValue(getClass().getClassLoader().getResourceAsStream("assets/tiles/tile.json"), Tile.class);

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