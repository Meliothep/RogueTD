package game;

import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import game.components.BulletComponent;
import game.components.EnemyComponent;
import game.components.TowerComponent;
import game.datas.EnemyData;
import game.datas.Way;
import game.datas.towerdatas.NormalTowerData;
import game.entities.Enemy;
import game.entities.ExpandButton;
import game.entities.Tower;
import game.entities.tile.Base;
import game.entities.tile.Tile;
import game.entities.tile.TileBuilder;
import game.exceptions.InvalidSpawnDataException;
import game.exceptions.TileBuilderException;
import game.strategies.FocusNearest;
import game.utils.Direction;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import java.util.List;

import static game.EntityType.BULLET;

public class GameEntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    private static final String entryField = "entry";
    private static final String validDirectionsField = "validDirections";
    private static final String tileField = "tile";
    private static final String targetField = "target";
    private static final String towerField = "tower";
    private static final String edataField = "enemyData";

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
        Tower tower = new Tower(new Point3D(data.getX(), data.getY(), data.getZ()));
        tower.addComponent(new TowerComponent(new NormalTowerData(data.get("multiplier"), new FocusNearest())));
        return tower;
    }

    @Spawns(value = "ENEMY")
    public Entity newEnemy(SpawnData data) throws InvalidSpawnDataException {
        if (!data.hasKey(tileField) || data.get(tileField) == null || !(data.get(tileField) instanceof Tile tile))
            throw new InvalidSpawnDataException("tile must be set in SpawnData");
        if (!data.hasKey(edataField) || !(data.get(edataField) instanceof EnemyData eData))
            throw new InvalidSpawnDataException("tile must be set in SpawnData");


        var enemy = new Enemy(Enemy.fromTile(tile));
        var list = GameState.getInstance().getWay(tile).getWaypoints();
        if (!list.contains(Enemy.fromTile(tile)))
            list.add(Enemy.fromTile(tile));
        var eComponent = new EnemyComponent(new Way(list), eData);
        enemy.addComponent(eComponent);
        enemy.addComponent(new HealthIntComponent(eData.hp()));
        return enemy;
    }

    @Spawns("BULLET")
    public Entity spawnBullet(SpawnData data) throws InvalidSpawnDataException {

        if (!data.hasKey(towerField) || data.get(towerField) == null || !(data.get(targetField) instanceof Entity))
            throw new InvalidSpawnDataException("entry must be set in SpawnData");

        Entity tower = data.get("tower");
        Entity target = data.get("target");

        var sphere = new Sphere(0.05);
        sphere.setMaterial(new PhongMaterial(Color.valueOf("#000000")));
        return new EntityBuilder(data)
                .type(BULLET)
                .viewWithBBox(sphere)
                .collidable()
                .with(new BulletComponent(tower, target))
                .with(new AutoRotationComponent())
                .build();
    }
}