package game.UI;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import game.UI.components.CustomMenuButton;
import game.datas.Config;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;

public class GameOverPane extends HBox {
    public GameOverPane() {
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.2);" +
                "-fx-alignment: center");

        var title = getUIFactoryService().newText("GAME OVER", Color.WHITE, FontType.GAME, 60);
        title.setStroke(Color.WHITESMOKE);
        title.setStrokeWidth(1.5);

        var menuBox = new VBox(
                2,
                new CustomMenuButton("New Game", () -> {
                    getGameWorld().reset();
                    setVisible(false);
                    setTranslateX(-Config.SCREEN_W);
                }),
                new CustomMenuButton("Main Menu", () -> FXGL.getGameController().gotoMainMenu())
        );
    }
}
