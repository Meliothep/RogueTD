package game.strategies;


import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;

import java.util.List;

public class FocusLeastHP implements FocusStrategy {
    @Override
    public FocusStrategies name() {
        return FocusStrategies.LeastHP;
    }

    @Override
    public Entity selectTarget(Entity tower, List<Entity> enemies) {
        enemies.sort((o1, o2) -> {
            var ec1 = o1.getComponent(HealthIntComponent.class);
            var ec2 = o2.getComponent(HealthIntComponent.class);
            return Double.compare(ec1.getValue(), ec2.getValue());
        });
        return enemies.stream().findFirst().get();
    }
}
