package game.eventhandlers;

import game.RogueTD;
import game.entities.Tower;
import game.entities.cell.FreeCell;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;

public class FreeCellClickHandler implements EventHandler<MouseEvent> {
    private final FreeCell cell;
    private final Point3D parentPosition;

    public FreeCellClickHandler(FreeCell cell, Point3D parentPosition) {
        this.cell = cell;
        this.parentPosition = parentPosition;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (cell.hasTower())
            return;
        Point3D coord = new Point3D(parentPosition.getX() + cell.getOrigin().getX(),
                -(0.6 + 0.2 * (cell.getMultiplier() - 1)),
                parentPosition.getZ() + cell.getOrigin().getZ());
        cell.setHasTower(true);
        Tower tower = RogueTD.spawnTower(coord);

        FilledCellClickHandler handler = new FilledCellClickHandler(cell, tower);
        tower.setHandler(handler);
        cell.setHandler(handler);
    }
}
