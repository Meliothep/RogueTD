package game.strategies;

import com.almasb.fxgl.entity.Entity;
import game.components.EnemyComponent;

import java.util.List;

public class FocusProgress implements FocusStrategy {
    @Override
    public FocusStrategies name() {
        return FocusStrategies.Progress;
    }

    @Override
    public Entity selectTarget(Entity tower, List<Entity> enemies) {
        enemies.sort((o1, o2) -> {
            var ec1 = o1.getComponent(EnemyComponent.class);
            var ec2 = o2.getComponent(EnemyComponent.class);

            if (ec1.getNextWaypoint() == ec2.getNextWaypoint()) {
                var d1 = o1.getPosition3D().distance(ec1.getNextWaypoint());
                var d2 = o2.getPosition3D().distance(ec2.getNextWaypoint());
                return Double.compare(d1, d2);
            } else if (ec1.getWaypoints().size() < ec2.getWaypoints().size()) {
                return -1;
            }
            return 1;
        });
        return enemies.stream().findFirst().get();
    }
}
