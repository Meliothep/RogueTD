package game.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import game.EntityType;
import game.datas.towerdatas.NormalTowerData;
import javafx.geometry.Point3D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.newLocalTimer;
import static com.almasb.fxgl.dsl.FXGL.spawn;


public class TowerComponent extends Component {

    private final NormalTowerData data;
    private LocalTimer shootTimer;

    public TowerComponent(NormalTowerData data) {
        this.data = data;
    }

    public int getDamage() {
        return data.attack();
    }

    @Override
    public void onAdded() {
        shootTimer = newLocalTimer();
        shootTimer.capture();
    }

    @Override
    public void onUpdate(double tpf) {
        if (shootTimer.elapsed(Duration.seconds(data.cooldown()))) {
            var enemies = FXGL.getGameWorld().getEntitiesByType(EntityType.ENEMY);
            enemies.removeIf(i -> i.getPosition3D().distance(entity.getPosition3D()) > data.range());
            if (!enemies.isEmpty()) {
                var target = data.focusStrategy().selectTarget(entity, enemies);
                shoot(target);
                shootTimer.capture();
            }
        }
    }

    private void shoot(Entity enemy) {
        Point3D position = new Point3D(getEntity().getX(), getEntity().getY() - 0.2, getEntity().getZ());
        data.gainXp(2);
        var bullet = spawn("BULLET",
                new SpawnData(position)
                        .put("tower", entity)
                        .put("target", enemy)
        );
    }

    public NormalTowerData getData() {
        return data;
    }
}