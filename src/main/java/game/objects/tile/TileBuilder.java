package game.objects.tile;

import game.Utils.Directions;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileBuilder {
    private Directions entry;
    private final List<Directions> validDirections = new ArrayList<>();
    private Point3D position = new Point3D(0,0,0);
    public TileBuilder withEntryIn(Directions direction){
        this.entry = direction;
        return this;
    }

    public TileBuilder setPosition(Point3D point){
        position = point;
        return this;
    }
    public TileBuilder setPosition(double x, double y, double z){
        position = new Point3D(x, y, z);
        return this;
    }
    public TileBuilder addValidDirections(Directions... directions){
        validDirections.addAll(List.of(directions));
        return this;
    }

    public Tile build(){
        TilePrototyp prototyp = null;
        if(validDirections.size() == 0){
            //TODO return TILE END
        }
        return null;
    }

    protected static Directions withSouthEntry(Directions entry, Directions exit){
        int index = Arrays.stream(Directions.values()).toList().indexOf(exit) + diffWithSouth(entry);
        return index >= 0 ? Arrays.stream(Directions.values()).toList().get(index%4) : Arrays.stream(Directions.values()).toList().get(4+index);
    }

    protected static int diffWithSouth(Directions direction){
        return Arrays.stream(Directions.values()).toList().indexOf(Directions.SOUTH) - Arrays.stream(Directions.values()).toList().indexOf(direction);
    }

    enum jsonDirections{
        straight(Directions.NORTH),
        rightAngle(Directions.EAST, Directions.WEST);
        jsonDirections(Directions... directions) {
            this.directions = Arrays.stream(directions).toList();
        }
        public final List<Directions> directions;
    }
}
