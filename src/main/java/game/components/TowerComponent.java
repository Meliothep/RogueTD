package game.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import game.EntityType;
import game.datas.towerdatas.TowerData;
import javafx.geometry.Point3D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * // TODO: assign bullet data from tower that shot it
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class TowerComponent extends Component {

    private final TowerData data;
    private LocalTimer shootTimer;

    public TowerComponent(TowerData data) {
        this.data = data;
    }

    public int getDamage() {
        return data.attack();
    }

    // TODO: read from data
    //public List<OnHitEffect> onHitEffects() { return List.of( new OnHitEffect(new SlowTimeEffect(0.2, Duration.seconds(3)), 0.75));}

    @Override
    public void onAdded() {
        shootTimer = newLocalTimer();
        shootTimer.capture();
    }

    @Override
    public void onUpdate(double tpf) {
        if (shootTimer.elapsed(Duration.seconds(data.cooldown()))) {

            getGameWorld()
                    .getClosestEntity(entity, e -> e.isType(EntityType.ENEMY))
                    .ifPresent(nearestEnemy -> {
                        if (nearestEnemy.getPosition3D().distance(entity.getPosition3D()) < data.range()) {
                            shoot(nearestEnemy);
                            shootTimer.capture();
                        }
                    });
        }
    }

    private void shoot(Entity enemy) {
        Point3D position = new Point3D(getEntity().getX(), getEntity().getY() - 0.2, getEntity().getZ());
        data.gainXp();
        var bullet = spawn("BULLET",
                new SpawnData(position)
                        .put("tower", entity)
                        .put("target", enemy)
        );
    }
}