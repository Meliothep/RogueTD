package game.eventhandlers;

import game.entities.cell.FreeCell;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;

public class FreeCellClickHandler implements EventHandler<MouseEvent> {
    private FreeCell cell;
    private Point3D parentPosition;

    public FreeCellClickHandler(FreeCell cell, Point3D parentPosition) {
        this.cell = cell;
        this.parentPosition = parentPosition;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        
    }
}
