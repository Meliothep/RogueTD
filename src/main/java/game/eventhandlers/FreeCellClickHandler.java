package game.eventhandlers;

import com.almasb.fxgl.dsl.FXGL;
import game.cell.FreeCell;
import game.entities.Tower;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;

import static game.SpawnHelper.spawnTower;
import static game.datas.Vars.MONEY;
import static game.datas.Vars.TOWER_COST;

public class FreeCellClickHandler implements EventHandler<MouseEvent> {
    private final FreeCell cell;
    private final Point3D parentPosition;

    public FreeCellClickHandler(FreeCell cell, Point3D parentPosition) {
        this.cell = cell;
        this.parentPosition = parentPosition;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.isSecondaryButtonDown() || cell.hasTower())
            return;
        if (FXGL.geti(MONEY) >= FXGL.geti(TOWER_COST)) {
            Point3D coord = new Point3D(parentPosition.getX() + cell.getOrigin().getX(),
                    -(0.6 + 0.2 * (cell.getMultiplier() - 1)),
                    parentPosition.getZ() + cell.getOrigin().getZ());
            cell.setHasTower(true);
            Tower tower = spawnTower(coord, cell.getMultiplier());

            FilledCellClickHandler handler = new FilledCellClickHandler(cell, tower);
            tower.setHandler(handler);
            cell.setHandler(handler);
            FXGL.inc(MONEY, -FXGL.geti(TOWER_COST));
            FXGL.inc(TOWER_COST, 25);
        } else {
            //TODO Display tempory message
        }
    }
}
