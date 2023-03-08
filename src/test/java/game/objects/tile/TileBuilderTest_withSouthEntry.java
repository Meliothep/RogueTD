package game.objects.tile;

import game.Utils.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileBuilderTest_withSouthEntry {

    @Test
    public void str_S(){
        Assertions.assertEquals(Direction.NORTH, TileBuilder.withSouthEntry(Direction.SOUTH, Direction.NORTH) );
    }
    @Test
    public void str_N(){
        Assertions.assertEquals(Direction.NORTH, TileBuilder.withSouthEntry(Direction.NORTH, Direction.SOUTH) );
    }
    @Test
    public void str_W(){
        Assertions.assertEquals(Direction.NORTH, TileBuilder.withSouthEntry(Direction.WEST, Direction.EAST));
    }
    @Test
    public void str_E(){
        Assertions.assertEquals(Direction.NORTH, TileBuilder.withSouthEntry(Direction.EAST, Direction.WEST));
    }

    @Test
    public void right_S(){
        Assertions.assertEquals(Direction.EAST, TileBuilder.withSouthEntry(Direction.SOUTH, Direction.EAST));
    }

    @Test
    public void right_E(){
        Assertions.assertEquals(Direction.EAST, TileBuilder.withSouthEntry(Direction.EAST, Direction.NORTH));
    }

    @Test
    public void right_N(){
        Assertions.assertEquals(Direction.EAST, TileBuilder.withSouthEntry(Direction.NORTH, Direction.WEST));
    }

    @Test
    public void left_S(){
        Assertions.assertEquals(Direction.WEST, TileBuilder.withSouthEntry(Direction.SOUTH, Direction.WEST));
    }

    @Test
    public void left_W(){
        Assertions.assertEquals(Direction.WEST, TileBuilder.withSouthEntry(Direction.WEST, Direction.NORTH));
    }

    @Test
    public void left_N(){
        Assertions.assertEquals(Direction.WEST, TileBuilder.withSouthEntry(Direction.NORTH, Direction.EAST));
    }
}
