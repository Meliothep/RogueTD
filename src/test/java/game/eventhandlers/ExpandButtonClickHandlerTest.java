package game.eventhandlers;

import game.GameState;
import game.utils.Direction;
import javafx.geometry.Point3D;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;

public class ExpandButtonClickHandlerTest {

    @BeforeAll
    public static void init() {
        GameState.getInstance().addTileOrigin(new Point3D(-2.8, 0, 0));
        GameState.getInstance().addTileOrigin(new Point3D(2.8, 0, 0));
        GameState.getInstance().addTileOrigin(new Point3D(0, 0, 2.8));
        GameState.getInstance().addTileOrigin(new Point3D(0, 0, -2.8));
    }


    @Test
    public void findValidDirections_allValid() {
        var result = ExpandButtonClickHandler.findValidDirections(GameState.getInstance().getTileOrigins(), new Point3D(0, 0, 8.2));
        assertThat(result, Matchers.containsInAnyOrder(Direction.values()));
    }

    @Test
    public void findValidDirections_allInvalid() {
        Assertions.assertEquals(new ArrayList<>(),
                ExpandButtonClickHandler.findValidDirections(GameState.getInstance().getTileOrigins(), new Point3D(0, 0, 0)));
    }
}
