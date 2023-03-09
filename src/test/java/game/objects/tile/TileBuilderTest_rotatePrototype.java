package game.objects.tile;

import game.Utils.Direction;
import javafx.geometry.Point3D;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class TileBuilderTest_rotatePrototype {
    private final List<Point3D> rightAngle = Arrays.asList(
            new Point3D(1.2, 0, 0),
            new Point3D(1.2, 0, 0.4),
            new Point3D(1.2, 0, 0.8),
            new Point3D(1.2, 0, 1.2),
            new Point3D(1.6, 0, 1.2),
            new Point3D(2, 0, 1.2),
            new Point3D(2.4, 0, 1.2)
    );
    private final List<Point3D> straight = Arrays.asList(
            new Point3D(1.2, 0, 0),
            new Point3D(1.2, 0, 0.4),
            new Point3D(1.2, 0, 0.8),
            new Point3D(1.2, 0, 1.2),
            new Point3D(1.2, 0, 1.6),
            new Point3D(1.2, 0, 2),
            new Point3D(1.2, 0, 2.4)
    );

    @Test
    public void rotatePrototype_rightSE() {
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(rightAngle);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.SOUTH, Direction.EAST);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(rightAngle.toArray()));
    }

    @Test
    public void rotatePrototype_leftSW() {
        //initialize
        List<Point3D> result = Arrays.asList(
                new Point3D(0, 0, 1.2),
                new Point3D(0.4, 0, 1.2),
                new Point3D(0.8, 0, 1.2),
                new Point3D(1.2, 0, 0),
                new Point3D(1.2, 0, 0.4),
                new Point3D(1.2, 0, 0.8),
                new Point3D(1.2, 0, 1.2)
        );
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(rightAngle);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.SOUTH, Direction.WEST);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(result.toArray()));
    }

    @Test
    public void rotatePrototype_rightEN() {
        //initialize
        List<Point3D> result = Arrays.asList(
                new Point3D(1.6, 0, 1.2),
                new Point3D(2, 0, 1.2),
                new Point3D(2.4, 0, 1.2),
                new Point3D(1.2, 0, 2.4),
                new Point3D(1.2, 0, 2),
                new Point3D(1.2, 0, 1.6),
                new Point3D(1.2, 0, 1.2)
        );
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(rightAngle);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.EAST, Direction.NORTH);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(result.toArray()));
    }

    @Test
    public void rotatePrototype_leftES() {
        //initialize
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(rightAngle);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.EAST, Direction.SOUTH);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(rightAngle.toArray()));
    }

    @Test
    public void rotatePrototype_rightNW() {
        //initialize
        List<Point3D> result = Arrays.asList(
                new Point3D(0, 0, 1.2),
                new Point3D(0.4, 0, 1.2),
                new Point3D(0.8, 0, 1.2),
                new Point3D(1.2, 0, 2.4),
                new Point3D(1.2, 0, 2),
                new Point3D(1.2, 0, 1.6),
                new Point3D(1.2, 0, 1.2)
        );
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(rightAngle);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.NORTH, Direction.WEST);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(result.toArray()));
    }

    @Test
    public void rotatePrototype_leftNE() {
        //initialize
        List<Point3D> result = Arrays.asList(
                new Point3D(1.6, 0, 1.2),
                new Point3D(2, 0, 1.2),
                new Point3D(2.4, 0, 1.2),
                new Point3D(1.2, 0, 2.4),
                new Point3D(1.2, 0, 2),
                new Point3D(1.2, 0, 1.6),
                new Point3D(1.2, 0, 1.2)
        );
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(rightAngle);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.NORTH, Direction.EAST);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(result.toArray()));
    }

    @Test
    public void rotatePrototype_rightWS() {
        //initialize
        List<Point3D> result = Arrays.asList(
                new Point3D(0, 0, 1.2),
                new Point3D(0.4, 0, 1.2),
                new Point3D(0.8, 0, 1.2),
                new Point3D(1.2, 0, 0),
                new Point3D(1.2, 0, 0.4),
                new Point3D(1.2, 0, 0.8),
                new Point3D(1.2, 0, 1.2)
        );
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(rightAngle);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.WEST, Direction.SOUTH);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(result.toArray()));
    }

    @Test
    public void rotatePrototype_leftWN() {
        //initialize
        List<Point3D> result = Arrays.asList(
                new Point3D(0, 0, 1.2),
                new Point3D(0.4, 0, 1.2),
                new Point3D(0.8, 0, 1.2),
                new Point3D(1.2, 0, 2.4),
                new Point3D(1.2, 0, 2),
                new Point3D(1.2, 0, 1.6),
                new Point3D(1.2, 0, 1.2)
        );
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(rightAngle);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.WEST, Direction.NORTH);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(result.toArray()));
    }

    @Test
    public void rotatePrototype_strSN() {
        //initialize
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(straight);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.SOUTH, Direction.NORTH);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(straight.toArray()));
    }

    @Test
    public void rotatePrototype_strNS() {
        //initialize
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(straight);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.NORTH, Direction.SOUTH);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(straight.toArray()));
    }


    @Test
    public void rotatePrototype_strWE() {
        //initialize
        List<Point3D> result = Arrays.asList(
                new Point3D(0, 0, 1.2),
                new Point3D(0.4, 0, 1.2),
                new Point3D(0.8, 0, 1.2),
                new Point3D(1.2, 0, 1.2),
                new Point3D(1.6, 0, 1.2),
                new Point3D(2, 0, 1.2),
                new Point3D(2.4, 0, 1.2)
        );

        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(straight);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.WEST, Direction.EAST);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(result.toArray()));
    }

    @Test
    public void rotatePrototype_strEW() {
        //initialize
        List<Point3D> result = Arrays.asList(
                new Point3D(0, 0, 1.2),
                new Point3D(0.4, 0, 1.2),
                new Point3D(0.8, 0, 1.2),
                new Point3D(1.2, 0, 1.2),
                new Point3D(1.6, 0, 1.2),
                new Point3D(2, 0, 1.2),
                new Point3D(2.4, 0, 1.2)
        );
        TilePrototype prototype = new TilePrototype();
        prototype.setWayCells(straight);
        //act
        TileBuilder.rotatePrototype(prototype, Direction.EAST, Direction.WEST);
        //assert
        assertThat(prototype.getWayCells(), Matchers.containsInAnyOrder(result.toArray()));
    }

}
