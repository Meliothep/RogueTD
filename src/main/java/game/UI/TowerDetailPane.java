package game.UI;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.awt.*;

public class TowerDetailPane extends VBox {

    private final Label levelLabel = new Label("0");
    private final Button levelupBtn = new Button();

    public TowerDetailPane() {
        super(3);
        setPrefSize(100, 75);
        setStyle("-fx-background-size: 150 100;\n" +
                "-fx-background-radius: 10;\n" +
                "-fx-border-radius: 10;\n" +
                "-fx-border-width:2;\n" +
                "-fx-border-color: #d2af6b;\n" +
                "-fx-background-color:#fee2aa");

        var levelVbox = new VBox();
        levelVbox.getChildren().add(new Label("level "));
        levelVbox.getChildren().add(levelLabel);

    }
}
