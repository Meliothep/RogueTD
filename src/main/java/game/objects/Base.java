package game.objects;

import com.almasb.fxgl.entity.Entity;
import game.EntityType;
import game.objects.cell.FreeCell;
import game.objects.cell.WayCell;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;

import static game.utils.Utils.round;

public class Base extends Entity {

    public Base(Point3D position) {
        setType(EntityType.BASE);
        setPosition3D(position);
        setView();
    }

    private void addGroundBoxToView(Point3D origin) {
        Box box = new Box(0.4, 0.4, 0.4);
        box.setTranslateX(origin.getX());
        box.setTranslateZ(origin.getZ());
        box.setMaterial(new PhongMaterial(Color.valueOf("#65cd50")));
        getViewComponent().addChild(box);
    }

    private void setView() {
        List<Point3D> origins = new ArrayList<>();

        origins.add(new Point3D(0.4, 0, 2));
        origins.add(new Point3D(0.4, 0, 0.4));
        origins.add(new Point3D(2, 0, 2));
        origins.add(new Point3D(2, 0, 0.4));

        origins.add(new Point3D(0.8, 0, 0.8));
        origins.add(new Point3D(0.8, 0, 1.2));
        origins.add(new Point3D(0.8, 0, 1.6));

        origins.add(new Point3D(1.2, 0, 0.8));
        origins.add(new Point3D(1.2, 0, 1.2));
        origins.add(new Point3D(1.2, 0, 1.6));

        origins.add(new Point3D(1.6, 0, 0.8));
        origins.add(new Point3D(1.6, 0, 1.2));
        origins.add(new Point3D(1.6, 0, 1.6));

        //add ground cell (just boxes)
        origins.forEach(this::addGroundBoxToView);

        //add wayCells
        Point3D point = new Point3D(1.2, 0, 2);
        origins.add(point);
        getViewComponent().addChild(new WayCell(point).getBox());

        point = new Point3D(1.2, 0, 2.4);
        origins.add(point);
        getViewComponent().addChild(new WayCell(point).getBox());

        // add freeCells
        for (int i = 0; i <= 6; i++) {
            double x = round(i * 0.4, 1);
            for (int j = 0; j <= 6; j++) {
                double z = round(j * 0.4, 1);
                point = new Point3D(x, 0, z);
                if (!origins.contains(point)) {
                    origins.add(point);
                    getViewComponent().addChild(new FreeCell(point, 1).getBox());
                }
            }
        }

    }

}
