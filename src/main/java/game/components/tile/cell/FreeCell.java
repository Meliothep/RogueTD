package game.components.tile.cell;

import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class FreeCell extends Cell{
    int multiplier;
    boolean filed = false;
    public FreeCell(Point3D origin, int multiplier) {
        super(origin, new Box(0.4, 0.4 + 0.2 * (multiplier-1) ,0.4 ));
        box.setTranslateY(-(0.4 + 0.2 * (multiplier-1))/2);
        box.setMaterial(new PhongMaterial(Color.valueOf("#65cd50")));
        box.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY){
                    box.setMaterial(new PhongMaterial(Color.RED));
                }
            }
        });
        this.multiplier = multiplier;
    }
}
