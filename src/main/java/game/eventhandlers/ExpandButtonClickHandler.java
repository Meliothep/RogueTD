package game.eventhandlers;

import game.GameState;
import game.RogueTD;
import game.entities.ExpandButton;
import game.utils.Direction;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static game.utils.Utils.round;

public class ExpandButtonClickHandler implements EventHandler<MouseEvent> {
    private final Point3D tileCoord;
    private final ExpandButton button;
    private final Direction entry;

    public ExpandButtonClickHandler(ExpandButton button, Point3D tileCoord, Direction entry) {
        this.tileCoord = tileCoord;
        this.button = button;
        this.entry = entry;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        List<Direction> validDirection = new ArrayList<>();
        List<Point3D> filledOrigin = GameState.getInstance().getTileOrigins();
        if (!filledOrigin.contains(new Point3D(tileCoord.getX(), 0, round(tileCoord.getZ() - 2.8, 1))))
            validDirection.add(Direction.SOUTH);
        if (!filledOrigin.contains(new Point3D(tileCoord.getX(), 0, round(tileCoord.getZ() + 2.8, 1))))
            validDirection.add(Direction.NORTH);
        if (!filledOrigin.contains(new Point3D(round(tileCoord.getX() - 2.8, 1), 0, tileCoord.getZ() - 2.8)))
            validDirection.add(Direction.EAST);
        if (!filledOrigin.contains(new Point3D(round(tileCoord.getX() + 2.8, 1), 0, tileCoord.getZ())))
            validDirection.add(Direction.WEST);
        RogueTD.despawnEntity(button);
        Direction futureEntry = Arrays.stream(Direction.values()).toList().get(Math.abs(Arrays.stream(Direction.values()).toList().indexOf(entry) - 2));
        RogueTD.spawnExpandButton(RogueTD.spawnTile(tileCoord, futureEntry, validDirection));
        GameState.getInstance().addTileOrigin(tileCoord);
    }
}
