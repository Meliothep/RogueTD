package game.entities.tile;

import game.utils.Direction;

import java.util.Arrays;
import java.util.List;

enum JsonDirections {
    straight(Direction.NORTH),
    rightAngle(Direction.EAST, Direction.WEST),
    strangeAngle(Direction.EAST, Direction.WEST),
    zigzag(Direction.NORTH);

    private final List<Direction> directions;

    JsonDirections(Direction... directions) {
        this.directions = Arrays.stream(directions).toList();
    }

    public List<Direction> getDirections() {
        return directions;
    }
}
