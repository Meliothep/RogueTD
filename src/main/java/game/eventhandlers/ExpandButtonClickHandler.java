package game.eventhandlers;

import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;

public class ExpandButtonClickHandler implements EventHandler<MouseEvent> {
    private final Point3D tileCoord;

    public ExpandButtonClickHandler(Point3D tileCoord) {
        this.tileCoord = tileCoord;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
