package game.strategies;

import com.almasb.fxgl.entity.Entity;

import java.util.List;

public class FocusNearest implements FocusStrategy {
    @Override
    public FocusStrategies name() {
        return FocusStrategies.Nearest;
    }

    @Override
    public Entity selectTarget(Entity tower, List<Entity> enemies) {
        enemies.sort((o1, o2) -> {
            var d1 = o1.getPosition3D().distance(tower.getPosition3D());
            var d2 = o2.getPosition3D().distance(tower.getPosition3D());
            return Double.compare(d2, d1);
        });
        return enemies.stream().findFirst().get();
    }
}
