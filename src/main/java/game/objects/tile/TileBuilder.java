package game.objects.tile;

import com.almasb.fxgl.dsl.EntityBuilder;
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
        if(validDirections.size() == 0){
            //TODO return TILE END
        }
        Directions choice = validDirections.size()>1 ?
                validDirections.get(randomIntBetween(1, validDirections.size())-1):
                validDirections.get(0);

        String fileName = selectFile(withSouthEntry(entry, choice));

        try {
            TilePrototyp prototype = TilePrototyp.fromJson(fileName);
            prototype.rotate(diffWithSouth(entry)*90);
            Tile tile = prototype.build();
            tile.setPosition3D(position);
            tile.setType(EntityType.TILE);
            for (Cell cell : tile.getCells()){
                tile.getViewComponent().addChild(cell.getbox());
            }
            return tile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected static String selectFile(Directions direction){
        List<JsonDirections> validFiles = new ArrayList<>();
        for (JsonDirections json : JsonDirections.values()){
            if (json.directions.contains(direction)){
                validFiles.add(json);
            }
        }
        return validFiles.size()>1 ?
                        validFiles.get(randomIntBetween(1, validFiles.size())-1).name():
                        validFiles.get(0).name();
    }

    protected static Directions withSouthEntry(Directions entry, Directions exit){
        int index = Arrays.stream(Directions.values()).toList().indexOf(exit) + diffWithSouth(entry);
        return index >= 0 ? Arrays.stream(Directions.values()).toList().get(index%4) : Arrays.stream(Directions.values()).toList().get(4+index);
    }

    private static int diffWithSouth(Directions direction){
        return Arrays.stream(Directions.values()).toList().indexOf(Directions.SOUTH) - Arrays.stream(Directions.values()).toList().indexOf(direction);
    }

    enum JsonDirections{
        straight(Directions.NORTH),
        rightAngle(Directions.EAST, Directions.WEST);
        JsonDirections(Directions... directions) {
            this.directions = Arrays.stream(directions).toList();
        }
        public final List<Directions> directions;
    }
}
