package game.objects.tile;

import javafx.geometry.Point3D;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class TilePrototypTest {
    private List<Point3D> rightAngle = Arrays.asList(
            new Point3D(1.2,0,0),
            new Point3D(1.2,0,0.4),
            new Point3D(1.2,0,0.8),
            new Point3D(1.2,0,1.2),
            new Point3D(1.6,0,1.2),
            new Point3D(2,0,1.2),
            new Point3D(2.4,0,1.2)
    );
    private List<Point3D> straight = Arrays.asList(
            new Point3D(1.2,0,0),
            new Point3D(1.2,0,0.4),
            new Point3D(1.2,0,0.8),
            new Point3D(1.2,0,1.2),
            new Point3D(1.2,0,1.6),
            new Point3D(1.2,0,2),
            new Point3D(1.2,0,2.4)
    );
    @Test
    public void fromJson_straight() throws IOException {
        TilePrototyp prototyp = TilePrototyp.fromJson("straight");
        Assertions.assertEquals(prototyp.getWayCells(), straight);
    }
    @Test
    public void fromJson_rightAngle() throws IOException {
        TilePrototyp prototyp = TilePrototyp.fromJson("rightAngle");
        Assertions.assertEquals(prototyp.getWayCells(), rightAngle);
    }
    @Test
    public void fromJson_throw() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {TilePrototyp.fromJson("azhgdsiua");});
    }

    @Test
    public void rotate_negative() {
        //initialize
        List<Point3D> rightAngleRN = Arrays.asList(
                new Point3D(0,0,1.2),
                new Point3D(0.4,0,1.2),
                new Point3D(0.8,0,1.2),
                new Point3D(1.2,0,0),
                new Point3D(1.2,0,0.4),
                new Point3D(1.2,0,0.8),
                new Point3D(1.2,0,1.2)
        );
        TilePrototyp prototyp = new TilePrototyp();
        prototyp.setWayCells(rightAngle);
        //act
        prototyp.rotate(-90);
        //assert
        assertThat(prototyp.getWayCells(), Matchers.containsInAnyOrder(rightAngleRN.toArray()));
    }

    @Test
    public void rotate_potitive() {
        //initialize
        List<Point3D> rightAngleRP = Arrays.asList(
                new Point3D(1.2,0,2.4),
                new Point3D(1.2,0,2),
                new Point3D(1.2,0,1.6),
                new Point3D(1.2,0,1.2),
                new Point3D(1.6,0,1.2),
                new Point3D(2,0,1.2),
                new Point3D(2.4,0,1.2)
        );
        TilePrototyp prototyp = new TilePrototyp();
        prototyp.setWayCells(rightAngle);
        //act
        prototyp.rotate(90);
        //assert
        assertThat(prototyp.getWayCells(), Matchers.containsInAnyOrder(rightAngleRP.toArray()));
    }
}
