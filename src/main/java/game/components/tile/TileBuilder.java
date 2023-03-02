package game.components.tile;

import game.Utils.Directions;

import java.util.ArrayList;
import java.util.List;

public class TileBuilder {
    private Directions entry;
    private List<Directions> invalidDirection = new ArrayList<>();
    private jsonDirections choice;
    private Directions currentEntry;
    private Directions currentDirection;

    public TileBuilder withEntryIn(Directions direction){
        this.entry = direction;
        return this;
    }

    public TileBuilder withInvalidDirection(Directions... directions){
        invalidDirection.addAll(List.of(directions));
        return this;
    }

    public Tile build(){

        return null;
    }

    enum jsonDirections{
        straight(Directions.NORTH),
        rightAngle(Directions.EST);
        jsonDirections(Directions directions) {}
    }
}
