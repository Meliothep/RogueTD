package game.components.tile;

import game.Utils.Directions;

import java.util.ArrayList;
import java.util.List;

import static game.Utils.Utils.randomIntBetween;

public class TileBuilder {
    private Directions entry;
    private List<Directions> validDirections = new ArrayList<>();
    private Directions currentEntry;
    private Directions currentDirection;

    public TileBuilder withEntryIn(Directions direction){
        this.entry = direction;
        return this;
    }

    public TileBuilder addValidDirections(Directions... directions){
        validDirections.addAll(List.of(directions));
        return this;
    }

    public Tile build(){
        var finalDirection = validDirections.get(randomIntBetween(0, validDirections.size()-1));
        return null;
    }

    enum jsonDirections{
        straight(Directions.NORTH),
        rightAngle(Directions.EST, Directions.WEST);
        jsonDirections(Directions... directions) {}
    }
}
