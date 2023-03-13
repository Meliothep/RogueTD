package game.eventhandlers;

import game.entities.ExpandButton;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;

public class ExpandButtonClickHandler implements EventHandler<MouseEvent> {
    private final Point3D tileCoord;
    private final ExpandButton button;

    public ExpandButtonClickHandler(ExpandButton button, Point3D tileCoord) {
        this.tileCoord = tileCoord;
        this.button = button;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
