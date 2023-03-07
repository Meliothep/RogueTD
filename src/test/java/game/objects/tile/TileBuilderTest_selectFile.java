package game.objects.tile;

import game.Utils.Directions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileBuilderTest_selectFile {
    @Test
    public void validFile_straight(){
        Assertions.assertEquals("straight", TileBuilder.selectFile(Directions.NORTH));
    }
    @Test
    public void validFile_rightAngleR(){
        Assertions.assertEquals("rightAngle", TileBuilder.selectFile(Directions.EAST));
    }
    @Test
    public void validFile_rightAngleL(){
        Assertions.assertEquals("rightAngle", TileBuilder.selectFile(Directions.WEST));
    }
}
