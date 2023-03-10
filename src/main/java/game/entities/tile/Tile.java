package game.entities.tile;

import com.almasb.fxgl.entity.Entity;
import game.entities.cell.Cell;
import game.entities.cell.FreeCell;
import game.entities.cell.WayCell;
import game.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Tile extends Entity {
    private List<FreeCell> freeCells;
    private List<WayCell> wayCells;
    private Direction entry;
    private Direction direction;

    public Tile() {
        freeCells = new ArrayList<>();
        wayCells = new ArrayList<>();
    }

    public void addFreeCell(FreeCell cell) {
        freeCells.add(cell);
    }

    public void addWayCell(WayCell cell) {
        wayCells.add(cell);
    }

    public List<FreeCell> getFreeCells() {
        return freeCells;
    }

    public List<WayCell> getWayCells() {
        return wayCells;
    }

    public List<Cell> getCells() {
        return Stream.concat(freeCells.stream(), wayCells.stream()).toList();
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
