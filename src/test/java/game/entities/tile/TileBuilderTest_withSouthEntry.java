package game.entities.tile;

import game.utils.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TileBuilderTest_withSouthEntry {

    @Test
    void str_S() {
        Assertions.assertEquals(Direction.NORTH, TileBuilderHelper.withSouthEntry(Direction.SOUTH, Direction.NORTH));
    }

    @Test
    void str_N() {
        Assertions.assertEquals(Direction.NORTH, TileBuilderHelper.withSouthEntry(Direction.NORTH, Direction.SOUTH));
    }

    @Test
    void str_W() {
        Assertions.assertEquals(Direction.NORTH, TileBuilderHelper.withSouthEntry(Direction.WEST, Direction.EAST));
    }

    @Test
    void str_E() {
        Assertions.assertEquals(Direction.NORTH, TileBuilderHelper.withSouthEntry(Direction.EAST, Direction.WEST));
    }

    @Test
    void right_S() {
        Assertions.assertEquals(Direction.EAST, TileBuilderHelper.withSouthEntry(Direction.SOUTH, Direction.EAST));
    }

    @Test
    void right_E() {
        Assertions.assertEquals(Direction.EAST, TileBuilderHelper.withSouthEntry(Direction.EAST, Direction.NORTH));
    }

    @Test
    void right_N() {
        Assertions.assertEquals(Direction.EAST, TileBuilderHelper.withSouthEntry(Direction.NORTH, Direction.WEST));
    }

    @Test
    void left_S() {
        Assertions.assertEquals(Direction.WEST, TileBuilderHelper.withSouthEntry(Direction.SOUTH, Direction.WEST));
    }

    @Test
    void left_W() {
        Assertions.assertEquals(Direction.WEST, TileBuilderHelper.withSouthEntry(Direction.WEST, Direction.NORTH));
    }

    @Test
    void left_N() {
        Assertions.assertEquals(Direction.WEST, TileBuilderHelper.withSouthEntry(Direction.NORTH, Direction.EAST));
    }
}
