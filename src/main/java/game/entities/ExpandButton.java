package game.entities;

import com.almasb.fxgl.entity.Entity;
import game.EntityType;
import game.entities.tile.Tile;
import game.eventhandlers.ExpandButtonClickHandler;
import javafx.geometry.Point3D;
import javafx.scene.shape.Box;

import static game.utils.Utils.round;

public class ExpandButton extends Entity {
    private Box crossx;
    private Box crossy;

    private ExpandButton(Point3D position) {
        setType(EntityType.EXPANDBUTTON);
        setPosition3D(position);
        setView();
    }

    public static ExpandButton fromTile(Tile tile) {
        if (tile.getDirection() == null)
            //TODO un truc a faire
            return null;
        Point3D buttonCoord = new Point3D(0, 0, 0);
        Point3D nextTileCoord = new Point3D(0, 0, 0);
        switch (tile.getDirection()) {
            case EAST -> {
                buttonCoord = new Point3D(tile.getPosition3D().getX() + 3.2, -0.3, tile.getPosition3D().getZ() + 1.2);
                nextTileCoord = new Point3D(tile.getPosition3D().getX() + 2.8, 0, tile.getPosition3D().getZ());
            }
            case WEST -> {
                buttonCoord = new Point3D(tile.getPosition3D().getX() - 0.8, -0.3, tile.getPosition3D().getZ() + 1.2);
                nextTileCoord = new Point3D(tile.getPosition3D().getX() - 2.8, 0, tile.getPosition3D().getZ());
            }
            case NORTH -> {
                buttonCoord = new Point3D(tile.getPosition3D().getX() + 1.2, -0.3, tile.getPosition3D().getZ() + 3.2);
                nextTileCoord = new Point3D(tile.getPosition3D().getX(), 0, tile.getPosition3D().getZ() + 2.8);
            }
            case SOUTH -> {
                buttonCoord = new Point3D(tile.getPosition3D().getX() + 1.2, -0.3, tile.getPosition3D().getZ() - 0.8);
                nextTileCoord = new Point3D(tile.getPosition3D().getX(), 0, tile.getPosition3D().getZ() - 2.8);
            }
        }
        nextTileCoord = new Point3D(round(nextTileCoord.getX(), 1), 0, round(nextTileCoord.getZ(), 1));
        ExpandButton button = new ExpandButton(buttonCoord);
        button.setHandler(new ExpandButtonClickHandler(button, nextTileCoord, tile));
        return button;
    }

    private void setView() {
        crossx = new Box(0.2, 0.1, 0.8);
        crossy = new Box(0.8, 0.1, 0.2);
        getViewComponent().addChild(crossx);
        getViewComponent().addChild(crossy);
    }

    private void setHandler(ExpandButtonClickHandler handler) {
        this.crossx.setOnMousePressed(handler);
        this.crossy.setOnMousePressed(handler);
    }
}
