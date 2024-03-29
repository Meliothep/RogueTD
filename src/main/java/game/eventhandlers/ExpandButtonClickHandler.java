package game.eventhandlers;

import com.almasb.fxgl.dsl.FXGL;
import game.GameState;
import game.RogueTD;
import game.entities.ExpandButton;
import game.entities.tile.Tile;
import game.utils.Direction;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import static game.SpawnHelper.spawnTile;
import static game.utils.Utils.round;

public class ExpandButtonClickHandler implements EventHandler<MouseEvent> {
    private final Point3D tileCoord;
    private final ExpandButton button;
    private final Tile lastTile;

    public ExpandButtonClickHandler(ExpandButton button, Point3D tileCoord, Tile tile) {
        this.tileCoord = tileCoord;
        this.button = button;
        this.lastTile = tile;
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
        FXGL.getGameWorld().removeEntity(button);
        try {
            Tile tile = spawnTile(tileCoord,
                    lastTile.getDirection().getOposite(),
                    findValidDirections(GameState.getInstance().getTileOrigins(), tileCoord));
            GameState.getInstance().addWayPoints(lastTile, tile);
            GameState.getInstance().addTileOrigin(tileCoord);
            GameState.getInstance().setLastTile(tile);
            FXGL.<RogueTD>getAppCast().onExpand();
        } catch (Exception ignored) {
        }
    }
}
