package game.entities.tile;

import game.EntityType;
import game.eventhandlers.FreeCellClickHandler;
import game.exceptions.TileBuilderException;
import game.objects.cell.FreeCell;
import game.utils.Direction;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static game.utils.Utils.randomIntBetween;
import static game.utils.Utils.rotatePoint;

public class TileBuilder {
    private final List<Direction> validDirections = new ArrayList<>();
    private Direction entry;
    private Point3D position;

    private Direction choice;

    protected static void rotatePrototype(TilePrototype prototype, Direction entry, Direction choice) {
        prototype.rotate(withSouthEntry(entry, choice) != Direction.WEST ? -diffWithSouth(entry) * 90 : (-diffWithSouth(entry) - 1) * 90);
    }

    protected static String selectFile(Direction direction) {
        List<JsonDirections> validFiles = new ArrayList<>();
        for (JsonDirections json : JsonDirections.values()) {
            if (json.directions.contains(direction)) {
                validFiles.add(json);
            }
        }
        return validFiles.size() > 1 ?
                validFiles.get(randomIntBetween(1, validFiles.size()) - 1).name() :
                validFiles.get(0).name();
    }

    protected static Direction withSouthEntry(Direction entry, Direction exit) {
        int index = Arrays.stream(Direction.values()).toList().indexOf(exit) + diffWithSouth(entry);
        return index >= 0 ? Arrays.stream(Direction.values()).toList().get(index % 4) : Arrays.stream(Direction.values()).toList().get(4 + index);
    }

    private static int diffWithSouth(Direction direction) {
        return Arrays.stream(Direction.values()).toList().indexOf(Direction.SOUTH) - Arrays.stream(Direction.values()).toList().indexOf(direction);
    }

    public TileBuilder withEntryIn(Direction direction) {
        this.entry = direction;
        return this;
    }

    public TileBuilder setPosition(Point3D point) {
        position = point;
        return this;
    }

    public TileBuilder setPosition(double x, double y, double z) {
        position = new Point3D(x, y, z);
        return this;
    }

    public TileBuilder addValidDirections(Direction... directions) {
        validDirections.addAll(List.of(directions));
        return this;
    }

    public TileBuilder addValidDirections(List<Direction> directions) {
        validDirections.addAll(directions);
        return this;
    }

    public Tile build() throws TileBuilderException {
        if (entry == null)
            throw new TileBuilderException("The entry direction must be set");
        if (position == null)
            throw new TileBuilderException("The position direction must be set");

        Tile tile = null;

        if (validDirections.isEmpty()) {
            tile = getPortalTile();
        } else {
            try {
                tile = fromJSon();
            } catch (IOException e) {
                throw new TileBuilderException("Problem occured while reading Json, pls verify ressources");
            }
        }

        tile.setPosition3D(position);
        tile.setType(EntityType.TILE);
        tile.setDirection(choice);
        tile.setEntry(entry);
        addFreeCellsView(tile);
        return tile;
    }

    private Tile getPortalTile() {
        choice = null;
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
        rotatePrototype(prototype, entry, entry);
        Tile tile = prototype.getTile();
        placePortal(tile);
        return tile;
    }

    private void placePortal(Tile tile) {
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

    private Tile fromJSon() throws IOException {
        calcChoice();
        String fileName = selectFile(withSouthEntry(entry, choice));
        TilePrototype prototype = TilePrototype.fromJson(fileName);
        rotatePrototype(prototype, entry, choice);
        return prototype.getTile();
    }

    private void calcChoice() {
        choice = validDirections.size() > 1 ?
                validDirections.get(randomIntBetween(1, validDirections.size()) - 1) :
                validDirections.get(0);
    }

    private void addFreeCellsView(Tile tile) {
        for (FreeCell cell : tile.getFreeCells()) {
            cell.setHandler(new FreeCellClickHandler(cell, tile.getPosition3D()));
            tile.getViewComponent().addChild(cell.getBox());
        }
    }

    enum JsonDirections {
        straight(Direction.NORTH),
        rightAngle(Direction.EAST, Direction.WEST),
        strangeAngle(Direction.EAST, Direction.WEST),
        zigzag(Direction.NORTH);

        private final List<Direction> directions;

        JsonDirections(Direction... directions) {
            this.directions = Arrays.stream(directions).toList();
        }
    }
}
