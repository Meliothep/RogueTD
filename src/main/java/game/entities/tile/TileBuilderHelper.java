package game.entities.tile;

import game.utils.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static game.utils.Utils.randomIntBetween;

class TileBuilderHelper {
    protected static void rotatePrototype(TilePrototype prototype, Direction entry, Direction choice) {
        if (withSouthEntry(entry, choice) == Direction.WEST) {
            Collections.reverse(prototype.getWayPoints());
        }
        prototype.rotate(withSouthEntry(entry, choice) != Direction.WEST ? -diffWithSouth(entry) * 90 : (-diffWithSouth(entry) - 1) * 90);
    }

    protected static String selectFile(Direction direction) {
        List<JsonDirections> validFiles = new ArrayList<>();
        for (JsonDirections json : JsonDirections.values()) {
            if (json.getDirections().contains(direction)) {
                validFiles.add(json);
            }
        }
        return validFiles.size() > 1 ?
                validFiles.get(randomIntBetween(1, validFiles.size()) - 1).name() :
                validFiles.get(0).name();
    }

    protected static Direction withSouthEntry(Direction entry, Direction exit) {
        int index = Arrays.stream(Direction.values()).toList().indexOf(exit) + diffWithSouth(entry);
        return index >= 0 ? Arrays.stream(Direction.values()).toList().get(index % 4) : Arrays.stream(Direction.values()).toList().get(4 + index);
    }

    protected static int diffWithSouth(Direction direction) {
        return Arrays.stream(Direction.values()).toList().indexOf(Direction.SOUTH) - Arrays.stream(Direction.values()).toList().indexOf(direction);
    }
}
