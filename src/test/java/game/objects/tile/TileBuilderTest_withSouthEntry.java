package game.objects.tile;

import game.Utils.Directions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileBuilderTest_withSouthEntry {

    @Test
    public void str_S(){
        Assertions.assertEquals(Directions.NORTH, TileBuilder.withSouthEntry(Directions.SOUTH, Directions.NORTH) );
    }
    @Test
    public void str_N(){
        Assertions.assertEquals(Directions.NORTH, TileBuilder.withSouthEntry(Directions.NORTH, Directions.SOUTH) );
    }
    @Test
    public void str_W(){
        Assertions.assertEquals(Directions.NORTH, TileBuilder.withSouthEntry(Directions.WEST, Directions.EAST));
    }
    @Test
    public void str_E(){
        Assertions.assertEquals(Directions.NORTH, TileBuilder.withSouthEntry(Directions.EAST, Directions.WEST));
    }

    @Test
    public void right_S(){
        Assertions.assertEquals(Directions.EAST, TileBuilder.withSouthEntry(Directions.SOUTH, Directions.EAST));
    }

    @Test
    public void right_E(){
        Assertions.assertEquals(Directions.EAST, TileBuilder.withSouthEntry(Directions.EAST, Directions.NORTH));
    }

    @Test
    public void right_N(){
        Assertions.assertEquals(Directions.EAST, TileBuilder.withSouthEntry(Directions.NORTH, Directions.WEST));
    }

    @Test
    public void left_S(){
        Assertions.assertEquals(Directions.WEST, TileBuilder.withSouthEntry(Directions.SOUTH, Directions.WEST));
    }

    @Test
    public void left_W(){
        Assertions.assertEquals(Directions.WEST, TileBuilder.withSouthEntry(Directions.WEST, Directions.NORTH));
    }

    @Test
    public void left_N(){
        Assertions.assertEquals(Directions.WEST, TileBuilder.withSouthEntry(Directions.NORTH, Directions.EAST));
    }
}
