package game.eventhandlers;

import game.entities.Tower;
import game.entities.cell.FreeCell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class FilledCellClickHandler implements EventHandler<MouseEvent> {
    private final Tower tower;
    private final FreeCell cell;

    public FilledCellClickHandler(FreeCell cell, Tower tower) {
        this.cell = cell;
        this.tower = tower;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("there is a tower right HERE");
    }
}
