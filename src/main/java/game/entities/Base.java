package game.entities;

import com.almasb.fxgl.entity.Entity;
import game.EntityType;
import game.entities.cell.FreeCell;
import game.entities.cell.WayCell;
import game.eventhandlers.FreeCellClickHandler;
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

    private void setView() {
        List<Point3D> origins = new ArrayList<>();

        origins.add(new Point3D(0.8, 0, 0.8));
        origins.add(new Point3D(0.8, 0, 1.2));
        origins.add(new Point3D(0.8, 0, 1.6));

        origins.add(new Point3D(1.2, 0, 0.8));
        origins.add(new Point3D(1.2, 0, 1.2));
        origins.add(new Point3D(1.2, 0, 1.6));

        origins.add(new Point3D(1.6, 0, 0.8));
        origins.add(new Point3D(1.6, 0, 1.2));
        origins.add(new Point3D(1.6, 0, 1.6));

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
                    FreeCell cell = new FreeCell(point, 1);
                    cell.setListener(new FreeCellClickHandler(cell, this.getPosition3D()));
                    getViewComponent().addChild(cell.getBox());
                }
            }
        }
        Box box = new Box(1.2, 1.5, 1.2);
        box.setTranslateX(1.2);
        box.setTranslateZ(1.2);
        box.setTranslateY(-1.5 / 2);
        box.setMaterial(new PhongMaterial(Color.valueOf("#1e1f22")));
        getViewComponent().addChild(box);
    }
}
