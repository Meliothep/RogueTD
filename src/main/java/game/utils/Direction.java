package game.utils;

public enum Direction {
    SOUTH, EAST, NORTH, WEST;

    public Direction getOposite() {
        switch (this) {
            case WEST -> {
                return EAST;
            }
            case EAST -> {
                return WEST;
            }
            case NORTH -> {
                return SOUTH;
            }
            case SOUTH -> {
                return NORTH;
            }
            default -> {
                return null;
            }
        }
    }
}
