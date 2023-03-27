package game.strategies;


import com.almasb.fxgl.entity.Entity;

import java.util.List;

public interface FocusStrategy {
    FocusStrategies name();

    Entity selectTarget(Entity tower, List<Entity> enemies);
}
