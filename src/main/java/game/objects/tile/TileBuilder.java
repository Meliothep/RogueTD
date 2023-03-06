package game.objects.tile;

import game.EntityType;
import game.Utils.Directions;
import game.objects.tile.cell.Cell;
import javafx.geometry.Point3D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static game.Utils.Utils.randomIntBetween;

public class TileBuilder {
    private Directions entry;
    private List<Directions> validDirections = new ArrayList<>();
    private Directions currentEntry;
    private Directions currentDirection;
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
        try {
            var finalDirection = validDirections.size()>1 ? validDirections.get(randomIntBetween(0, validDirections.size())) : validDirections.get(0);
            var diff = Arrays.stream(Directions.values()).toList().indexOf(finalDirection) - Arrays.stream(Directions.values()).toList().indexOf(Directions.NORTH);
            var adjFinalDirection = Directions.values()[Math.abs((Arrays.stream(Directions.values()).toList().indexOf(finalDirection) + diff)%4)];

            if (jsonDirections.straight.directions.contains(adjFinalDirection)){
                prototyp = TilePrototyp.fromJson(jsonDirections.straight.name());
            }else if (jsonDirections.rightAngle.directions.contains(adjFinalDirection)){

                prototyp = TilePrototyp.fromJson(jsonDirections.rightAngle.name());
            }
            for (int i = 0; i < Math.abs(diff); i++) {
                prototyp.rotate(diff<0 ? 90 : -90);
            }
            var tile = prototyp.build();
            tile.setType(EntityType.TILE);
            tile.setPosition3D(position);
            for (Cell cell : tile.getCells()){
                tile.getViewComponent().addChild(cell.getbox());
            }
            return tile;
        } catch (IOException e) {
            return null;
        }
    }

    enum jsonDirections{
        straight(Directions.NORTH),
        rightAngle(Directions.EST, Directions.WEST);
        jsonDirections(Directions... directions) {
            this.directions = Arrays.stream(directions).toList();
        }
        public final List<Directions> directions;
    }
}
