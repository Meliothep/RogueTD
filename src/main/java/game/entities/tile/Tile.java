package game.entities.tile;

import com.almasb.fxgl.entity.Entity;
import game.objects.cell.FreeCell;
import game.utils.Direction;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Entity {
    private final List<FreeCell> freeCells;
    private Direction entry;
    private Direction direction;

    public Tile() {
        freeCells = new ArrayList<>();
    }

    public void addFreeCell(FreeCell cell) {
        freeCells.add(cell);
    }

    public List<FreeCell> getFreeCells() {
        return freeCells;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getEntry() {
        return entry;
    }

    public void setEntry(Direction entry) {
        this.entry = entry;
    }

}
