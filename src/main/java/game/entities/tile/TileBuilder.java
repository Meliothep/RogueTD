package game.entities.tile;

import game.EntityType;
import game.cell.FreeCell;
import game.eventhandlers.FreeCellClickHandler;
import game.exceptions.TileBuilderException;
import game.utils.Direction;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static game.entities.tile.TileBuilderHelper.*;
import static game.utils.Utils.randomIntBetween;

public class TileBuilder {
    private final List<Direction> validDirections = new ArrayList<>();
    private Direction entry;
    private Point3D position;

    private Direction choice;

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
            choice = null;
            tile = Portal.getPortal(entry);
        } else {
            try {
                tile = fromJSon();
            } catch (IOException e) {
                throw new TileBuilderException("Problem occured while reading Json, pls verify ressources");
            }
        }
        tile.setWayPoints(tile.getWayPoints().stream().map((Point3D it) -> {
            return new Point3D(position.getX() + it.getX(), position.getY() + it.getY(), position.getZ() + it.getZ());
        }).toList());

        tile.setPosition3D(position);
        tile.setType(EntityType.TILE);
        tile.setDirection(choice);
        tile.setEntry(entry);

        addFreeCellsView(tile);
        return tile;
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
}
