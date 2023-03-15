package game.eventhandlers;

import game.GameState;
import game.RogueTD;
import game.entities.ExpandButton;
import game.utils.Direction;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
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

    protected static List<Direction> findValidDirections(List<Point3D> filledOrigin, Point3D tileCoord) {
        List<Direction> validDirection = new ArrayList<>();
        if (!filledOrigin.contains(new Point3D(tileCoord.getX(), 0, round(tileCoord.getZ() - 2.8, 1))))
            validDirection.add(Direction.SOUTH);
        if (!filledOrigin.contains(new Point3D(tileCoord.getX(), 0, round(tileCoord.getZ() + 2.8, 1))))
            validDirection.add(Direction.NORTH);
        if (!filledOrigin.contains(new Point3D(round(tileCoord.getX() - 2.8, 1), 0, tileCoord.getZ())))
            validDirection.add(Direction.WEST);
        if (!filledOrigin.contains(new Point3D(round(tileCoord.getX() + 2.8, 1), 0, tileCoord.getZ())))
            validDirection.add(Direction.EAST);
        return validDirection;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        RogueTD.despawnEntity(button);
        System.out.println(findValidDirections(GameState.getInstance().getTileOrigins(), tileCoord));
        System.out.println(entry.getOposite());
        RogueTD.spawnExpandButton(RogueTD.spawnTile(tileCoord,
                entry.getOposite(),
                findValidDirections(GameState.getInstance().getTileOrigins(), tileCoord)));
        GameState.getInstance().addTileOrigin(tileCoord);
    }
}
