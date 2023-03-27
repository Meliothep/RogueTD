package game.entities.tile;

import game.utils.Direction;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.Arrays;
import java.util.List;

import static game.utils.Utils.rotatePoint;

class Portal {

    public static Tile getPortal(Direction entry) {
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(List.of(
                //ways
                new Point3D(1.2, 0, 0),
                new Point3D(1.2, 0, 0.4),
                new Point3D(1.2, 0, 0.8),
                //portal zone
                new Point3D(0.4, 0, 1.2),
                new Point3D(0.8, 0, 1.2),
                new Point3D(1.2, 0, 1.2),
                new Point3D(1.6, 0, 1.2),
                new Point3D(2, 0, 1.2)));
        TileBuilderHelper.rotatePrototype(prototype, entry, entry);
        Tile tile = prototype.getTile();
        placePortal(tile, entry);
        return tile;
    }

    private static void placePortal(Tile tile, Direction entry) {
        Point3D roofDim = new Point3D(0.2, 0.2, 1.6);
        Point3D roofLoc = new Point3D(1.2, -1.6, 1.2);

        Point3D rightPillarDim = new Point3D(0.2, 1.6, 0.2);
        Point3D rightPillarLoc = new Point3D(1.2, -0.8, 0.5);

        Point3D leftPillarDim = new Point3D(0.2, 1.6, 0.2);
        Point3D leftPillarLoc = new Point3D(1.2, -1.6 / 2, 0.5 + 1.4);

        Point3D portalDim = new Point3D(0.1, 1.5, 1.4);
        Point3D portalLoc = new Point3D(1.2, -1.6 / 2, 1.2);

        if ((Arrays.stream(Direction.values()).toList().indexOf(entry) + 1) % 2 == 1) {
            roofDim = new Point3D(1.6, 0.2, 0.2);
            roofLoc = rotatePoint(roofLoc, 90);

            rightPillarLoc = rotatePoint(rightPillarLoc, 90);
            leftPillarLoc = rotatePoint(leftPillarLoc, 90);

            portalDim = new Point3D(1.4, 1.5, 0.1);
            portalLoc = rotatePoint(portalLoc, 90);
        }
        Box roof = new Box(roofDim.getX(), roofDim.getY(), roofDim.getZ());
        roof.setMaterial(new PhongMaterial(Color.valueOf("#1e1f22")));
        roof.setTranslateX(roofLoc.getX());
        roof.setTranslateY(roofLoc.getY());
        roof.setTranslateZ(roofLoc.getZ());

        Box rightPillar = new Box(rightPillarDim.getX(), rightPillarDim.getY(), rightPillarDim.getZ());
        rightPillar.setMaterial(new PhongMaterial(Color.valueOf("#1e1f22")));
        rightPillar.setTranslateX(rightPillarLoc.getX());
        rightPillar.setTranslateY(rightPillarLoc.getY());
        rightPillar.setTranslateZ(rightPillarLoc.getZ());

        Box leftPillar = new Box(leftPillarDim.getX(), leftPillarDim.getY(), leftPillarDim.getZ());
        leftPillar.setMaterial(new PhongMaterial(Color.valueOf("#1e1f22")));
        leftPillar.setTranslateX(leftPillarLoc.getX());
        leftPillar.setTranslateY(leftPillarLoc.getY());
        leftPillar.setTranslateZ(leftPillarLoc.getZ());

        Box portal = new Box(portalDim.getX(), portalDim.getY(), portalDim.getZ());
        portal.setMaterial(new PhongMaterial(Color.valueOf("#CC8899")));
        portal.setTranslateX(portalLoc.getX());
        portal.setTranslateY(portalLoc.getY());
        portal.setTranslateZ(portalLoc.getZ());

        tile.getViewComponent().addChild(roof);

        tile.getViewComponent().addChild(rightPillar);
        tile.getViewComponent().addChild(leftPillar);
        tile.getViewComponent().addChild(portal);
    }
}
