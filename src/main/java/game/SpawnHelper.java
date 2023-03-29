package game;

import com.almasb.fxgl.entity.SpawnData;
import game.datas.EnemyData;
import game.datas.WaveData;
import game.entities.ExpandButton;
import game.entities.Tower;
import game.entities.tile.Tile;
import game.exceptions.CantSpawnButtonException;
import game.utils.Direction;
import game.utils.Utils;
import javafx.geometry.Point3D;
import javafx.util.Duration;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;
import static game.datas.Vars.CURRENT_WAVE;
import static game.datas.Vars.NUM_ENEMIES;

public class SpawnHelper {
    public static void spawnWave() {
        inc(CURRENT_WAVE, 1);
        var wdata = new WaveData(geti(CURRENT_WAVE));

        var hpPool = wdata.getHpPool();

        for (int i = 0; i < wdata.getEnemyCount(); i++) {
            var hp = hpPool;
            if (i != wdata.getEnemyCount() - 1) {
                var a = hpPool / (wdata.getEnemyCount() - i);
                var b = hpPool / (wdata.getEnemyCount() - i) * 0.1;
                hp = Utils.randomIntBetween((int) (a - b), (int) (a + b));
                hpPool -= hp;
            }

            EnemyData edata = new EnemyData(
                    hp,
                    (int) ((Math.log(geti(CURRENT_WAVE)) + 1) * 7 + Math.pow(hp, 0.33)),
                    0.02,
                    0.4);

            runOnce(() -> {
                spawn("ENEMY",
                        new SpawnData()
                                .put("tile", GameState.getInstance().getLastTile())
                                .put("enemyData", edata)
                );

            }, Duration.seconds(i * edata.interval()));
        }

        inc(NUM_ENEMIES, wdata.getEnemyCount());
    }

    public static Tile spawnTile(Point3D position, Direction entry, List<Direction> directions) {
        SpawnData data = new SpawnData(position);
        data.put("entry", entry);
        data.put("validDirections", directions);

        return (Tile) spawn("TILE", data);
    }

    public static Tower spawnTower(Point3D position, int multiplier) {
        SpawnData data = new SpawnData(position);
        data.put("multiplier", multiplier);
        return (Tower) spawn("TOWER", data);
    }

    public static ExpandButton spawnExpandButton(Tile tile) throws CantSpawnButtonException {
        SpawnData data = new SpawnData();
        data.put("tile", tile);
        if (tile.getDirection() == null) {
            throw new CantSpawnButtonException("aucune direction donné a la derniere tile");
        }
        return (ExpandButton) spawn("EXPANDBUTTON", data);
    }
}
