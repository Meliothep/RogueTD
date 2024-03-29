package game.entities.tile;

import game.utils.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TileBuilderTest_selectFile {
    @Test
    void validFile_straight() {
        var filename = TileBuilderHelper.selectFile(Direction.NORTH);
        Assertions.assertTrue((filename == "straight") || (filename == "zigzag"));
    }

    @Test
    void validFile_rightAngleR() {
        var filename = TileBuilderHelper.selectFile(Direction.EAST);
        Assertions.assertTrue((filename == "rightAngle") || (filename == "strangeAngle"));
    }

    @Test
    void validFile_rightAngleL() {
        var filename = TileBuilderHelper.selectFile(Direction.WEST);
        Assertions.assertTrue((filename == "rightAngle") || (filename == "strangeAngle"));
    }
}
