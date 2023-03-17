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
        }else{
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
                new Point3D(1.2,0,0),
                new Point3D(1.2,0,0.4),
                new Point3D(1.2,0,0.8),
                //portal zone
                new Point3D(0.4,0,1.2),
                new Point3D(0.8,0,1.2),
                new Point3D(1.2,0,1.2),
                new Point3D(1.6,0,1.2),
                new Point3D(2,0,1.2)));
        // LA LIGNE EN DESSOUS
        rotatePrototype(prototype, Direction.SOUTH, entry);
        Tile tile = prototype.getTile();
        if ((Arrays.stream(Direction.values()).toList().indexOf(entry)+1)%2 != 1){
            //TODO rajoute le monumet a l'horizontal
        }else{
            //TODO rajoute le monumet a la vertical
        }
        return tile;
    }

    private Tile fromJSon() throws IOException {
        calcChoice();
        String fileName = selectFile(withSouthEntry(entry, choice));
        TilePrototype prototype = TilePrototype.fromJson(fileName);
        rotatePrototype(prototype, entry, choice);
        return prototype.getTile();
    }

    private void calcChoice(){
        choice = validDirections.size() > 1 ?
                validDirections.get(randomIntBetween(1, validDirections.size()) - 1) :
                validDirections.get(0);
    }
    private void addFreeCellsView(Tile tile){
        for (FreeCell cell : tile.getFreeCells()) {
            cell.setHandler(new FreeCellClickHandler(cell, tile.getPosition3D()));
            tile.getViewComponent().addChild(cell.getBox());
        }
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
