package game.eventhandlers;

import com.almasb.fxgl.dsl.FXGL;
import game.RogueTD;
import game.cell.FreeCell;
import game.components.TowerComponent;
import game.entities.Tower;
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
        FXGL.<RogueTD>getAppCast().onTowerClick(tower.getComponent(TowerComponent.class).getData());
    }
}
