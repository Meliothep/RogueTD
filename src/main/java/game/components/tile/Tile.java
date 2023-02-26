package game.components.tile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import game.components.tile.cell.Cell;
import game.components.tile.cell.FreeCell;
import game.components.tile.cell.WayCell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@JsonRootName(value = "Tile")
@JsonDeserialize(using = TileDeserializer.class)
public class Tile {
    private List<FreeCell> freeCells;
    private List<WayCell> wayCells;

    public Tile(){
        freeCells = new ArrayList<>();
        wayCells = new ArrayList<>();
    }

    public void addFreeCell(FreeCell cell){ freeCells.add(cell); }
    public void addWayCell(WayCell cell){ wayCells.add(cell); }

    @JsonIgnore
    public List<FreeCell> getFreeCells() { return freeCells; }

    public List<WayCell> getWayCells() { return wayCells; }
    @JsonIgnore
    public List<Cell> getCells() { return Stream.concat(freeCells.stream(), wayCells.stream()).toList(); }
}
