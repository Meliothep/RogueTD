package game.UI;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import game.UI.components.CustomMenuButton;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import static game.datas.Config.SCREEN_H;
import static game.datas.Config.SCREEN_W;

public class GameOverPane extends VBox {
    public GameOverPane() {
        setPrefSize(SCREEN_W + 50, SCREEN_H);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.2);" +
                "-fx-alignment: center");

        var title = getUIFactoryService().newText("GAME OVER", Color.WHITE, FontType.GAME, 60);
        title.setStroke(Color.WHITESMOKE);
        title.setStrokeWidth(1.5);
        title.setStyle("-fx-border-width: 20");

        title.setTranslateY(-20);

        var menuBox = new VBox(
                new CustomMenuButton("Main Menu", () -> FXGL.getGameController().gotoMainMenu(), 200, 30)
        );

        menuBox.setAlignment(Pos.TOP_CENTER);

        getChildren().addAll(title, menuBox);


    }
}
