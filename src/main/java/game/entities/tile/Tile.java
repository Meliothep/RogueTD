package game.entities.tile;

import com.almasb.fxgl.entity.Entity;
import game.objects.cell.FreeCell;
import game.utils.Direction;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Entity {
    private final List<FreeCell> freeCells;
    private Direction entry;
    private Direction direction;
    private List<Point3D> wayPoints = new ArrayList<>();

    public Tile() {
        freeCells = new ArrayList<>();
    }

    public List<Point3D> getWayPoints() {
        return wayPoints;
    }

    protected void setWayPoints(List<Point3D> wayCells) {
        this.wayPoints = wayCells;
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
