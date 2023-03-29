package game.components;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import game.RogueTD;
import game.datas.Config;
import javafx.geometry.Point3D;


public class BulletComponent extends Component {

    private final Entity tower;
    private final Entity target;

    public BulletComponent(Entity tower, Entity target) {
        this.tower = tower;
        this.target = target;
    }

    @Override
    public void onUpdate(double tpf) {
        if (!target.isActive()) {
            entity.removeFromWorld();
            return;
        }

        if (entity.distanceBBox(target) < Config.BULLET_SPEED * tpf) {
            onTargetHit();
            return;
        }
        Point3D velocity = target.getPosition3D().subtract(entity.getPosition3D())
                .normalize()
                .multiply(Config.BULLET_SPEED);

        entity.translate3D(velocity);
    }

    private void onTargetHit() {
        TowerComponent data = tower.getComponent(TowerComponent.class);

        entity.removeFromWorld();

        var hp = target.getComponent(HealthIntComponent.class);

        hp.damage(data.getDamage());
        if (hp.isZero()) {
            FXGL.<RogueTD>getAppCast().onEnemyKilled(target);
            target.removeFromWorld();
        }
    }
}