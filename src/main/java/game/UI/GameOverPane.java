package game.UI;

import javafx.scene.layout.VBox;

import static game.datas.Config.SCREEN_H;
import static game.datas.Config.SCREEN_W;

public class GameOverPane extends VBox {

    public GameOverPane() {
        super(3);
        setPrefSize(SCREEN_W + 50, SCREEN_H);
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.2);" +
                "-fx-alignment: center");

        
    }
}
