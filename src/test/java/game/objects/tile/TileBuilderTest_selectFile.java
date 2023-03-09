package game.objects.tile;

import game.Utils.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileBuilderTest_selectFile {
    @Test
    public void validFile_straight() {
        Assertions.assertEquals("straight", TileBuilder.selectFile(Direction.NORTH));
    }

    @Test
    public void validFile_rightAngleR() {
        Assertions.assertEquals("rightAngle", TileBuilder.selectFile(Direction.EAST));
    }

    @Test
    public void validFile_rightAngleL() {
        Assertions.assertEquals("rightAngle", TileBuilder.selectFile(Direction.WEST));
    }
}
