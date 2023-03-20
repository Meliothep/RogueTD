package game.components;

import com.almasb.fxgl.entity.component.Component;
import game.datas.EnemyData;
import game.datas.Way;
import javafx.geometry.Point3D;

import java.util.List;

public class EnemyComponent extends Component {

    private final List<Point3D> waypoints;
    private final EnemyData data;
    private Point3D nextWaypoint;

    public EnemyComponent(Way way, EnemyData data) {
        waypoints = way.getWaypoints();
        this.data = data;
    }

    public EnemyData getData() {
        return data;
    }

    @Override
    public void onAdded() {
        nextWaypoint = waypoints.remove(0);

        entity.setPosition3D(nextWaypoint);
    }

    @Override
    public void onUpdate(double tpf) {
        double speed = tpf * 60 * data.moveSpeed();

        Point3D velocity = nextWaypoint.subtract(entity.getPosition3D())
                .normalize()
                .multiply(speed);

        entity.translate3D(velocity);

        if (nextWaypoint.distance(entity.getPosition3D()) < speed) {
            entity.setPosition3D(nextWaypoint);

            if (!waypoints.isEmpty()) {
                nextWaypoint = waypoints.remove(0);
            } else {
                //FXGL.<TowerDefenseApp>getAppCast().onEnemyReachedEnd(entity);

                entity.removeFromWorld();
            }
        }
    }
}