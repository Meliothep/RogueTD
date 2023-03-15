package game.entities.tile;

import game.EntityType;
import game.eventhandlers.FreeCellClickHandler;
import game.exceptions.TileBuilderException;
import game.objects.cell.FreeCell;
import game.utils.Direction;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static game.utils.Utils.randomIntBetween;

public class TileBuilder {
    private final List<Direction> validDirections = new ArrayList<>();
    private Direction entry;
    private Point3D position;

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

        if (validDirections.isEmpty()) {
            //TODO return tile de fin
        }
        Direction choice = validDirections.size() > 1 ?
                validDirections.get(randomIntBetween(1, validDirections.size()) - 1) :
                validDirections.get(0);

        String fileName = selectFile(withSouthEntry(entry, choice));

        try {
            TilePrototype prototype = TilePrototype.fromJson(fileName);
            rotatePrototype(prototype, entry, choice);
            Tile tile = prototype.getTile();
            tile.setPosition3D(position);
            tile.setType(EntityType.TILE);
            tile.setDirection(choice);
            tile.setEntry(entry);
            for (FreeCell cell : tile.getFreeCells()) {
                cell.setHandler(new FreeCellClickHandler(cell, tile.getPosition3D()));
                tile.getViewComponent().addChild(cell.getBox());
            }
            return tile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    enum JsonDirections {
        straight(Direction.NORTH),
        rightAngle(Direction.EAST, Direction.WEST);

        private final List<Direction> directions;

        JsonDirections(Direction... directions) {
            this.directions = Arrays.stream(directions).toList();
        }
    }
}
