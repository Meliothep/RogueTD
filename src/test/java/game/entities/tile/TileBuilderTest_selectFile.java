package game.entities.tile;

import game.utils.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TileBuilderTest_selectFile {
    @Test
    void validFile_straight() {
        Assertions.assertEquals("straight", TileBuilder.selectFile(Direction.NORTH));
    }

    @Test
    void validFile_rightAngleR() {
        Assertions.assertEquals("rightAngle", TileBuilder.selectFile(Direction.EAST));
    }

    @Test
    void validFile_rightAngleL() {
        Assertions.assertEquals("rightAngle", TileBuilder.selectFile(Direction.WEST));
    }
}
