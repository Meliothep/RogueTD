package game.UI;


import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.view.KeyView;
import com.almasb.fxgl.input.view.MouseButtonView;
import com.almasb.fxgl.scene.Scene;
import game.datas.Config;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import static javafx.scene.input.KeyCode.*;

public class GameMenu extends FXGLMenu {

    private final VBox scoresRoot = new VBox(10);
    private final Node highScores;
    private final boolean isLoadedScore = false;

    public GameMenu() {
        super(MenuType.MAIN_MENU);
        var bg = new Rectangle(Config.SCREEN_W, Config.SCREEN_H);
        bg.setFill(Color.valueOf("#7985ab"));
        getContentRoot().getChildren().setAll(new Rectangle(getAppWidth(), getAppHeight()));

        var title = getUIFactoryService().newText(getSettings().getTitle(), Color.WHITE, 46.0);
        title.setStroke(Color.WHITESMOKE);
        title.setStrokeWidth(1.5);

        if (!FXGL.isMobile()) {
            title.setEffect(new Bloom(0.6));
        }
        centerTextBind(title, getAppWidth() / 2.0, 200);

        var version = getUIFactoryService().newText(getSettings().getVersion(), Color.WHITE, 22.0);
        centerTextBind(version, getAppWidth() / 2.0, 220);

        getContentRoot().getChildren().addAll(title, version);


        var menuBox = new VBox(
                5,
                new MenuButton("New Game", () -> fireNewGame()),
                new MenuButton("High Scores", () -> toggleHighScores()),
                new MenuButton("How to Play", () -> instructions()),
                new MenuButton("Exit", () -> fireExit())
        );


        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(getAppWidth() / 2.0 - 125);
        menuBox.setTranslateY(getAppHeight() / 2.0);

        // useful for checking if nodes are properly centered
        var centeringLine = new Line(getAppWidth() / 2.0, 0, getAppWidth() / 2.0, getAppHeight());
        centeringLine.setStroke(Color.WHITE);

        scoresRoot.setPadding(new Insets(10));
        scoresRoot.setAlignment(Pos.TOP_LEFT);

        StackPane hsRoot = new StackPane();
        hsRoot.setAlignment(Pos.TOP_CENTER);
        hsRoot.setCache(true);
        hsRoot.setCacheHint(CacheHint.SPEED);

        highScores = hsRoot;


        getContentRoot().getChildren().addAll(bg, menuBox, hsRoot);
    }

    @Override
    public void onCreate() {
        if (isLoadedScore)
            return;
    }

    @Override
    public void onEnteredFrom(Scene prevState) {
    }


    private void toggleHighScores() {
        animationBuilder(this)
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .translate(highScores)
                .from(new Point2D(getAppWidth(), highScores.getTranslateY()))
                .to(new Point2D(getAppWidth() - 450, highScores.getTranslateY()))
                .build().start();
    }

    private void instructions() {
        GridPane pane = new GridPane();
        if (!FXGL.isMobile()) {
            pane.setEffect(new DropShadow(5, 3.5, 3.5, Color.BLUE));
        }
        pane.setHgap(25);
        pane.setVgap(10);
        pane.addRow(0, getUIFactoryService().newText("Movement"), new HBox(4, new KeyView(W), new KeyView(S), new KeyView(A), new KeyView(D)));
        pane.addRow(1, getUIFactoryService().newText("Shoot"), new MouseButtonView(MouseButton.PRIMARY));

        getDialogService().showBox("How to Play", pane, getUIFactoryService().newButton("OK"));
    }

    private static class MenuButton extends Parent {
        MenuButton(String name, Runnable action) {
            var box = new HBox();
            box.setStyle("-fx-background-radius: 15;\n" +
                    "-fx-border-radius: 15;\n" +
                    "-fx-border-width:2;\n" +
                    "-fx-border-color: #fee2aa;\n" +
                    "-fx-background-color:#d8b571;\n" +
                    "-fx-padding: 7;");

            var text = getUIFactoryService().newText(name, Color.valueOf("#fee2aa"), 30);
            text.setStrokeWidth(1.5);
            text.strokeProperty().bind(text.fillProperty());

            text.fillProperty().bind(
                    Bindings.when(hoverProperty())
                            .then(Color.valueOf("#fee2aa"))
                            .otherwise(Color.WHITE)
            );

            setOnMouseClicked(e -> action.run());

            setPickOnBounds(true);

            box.getChildren().add(text);

            getChildren().add(box);
        }
    }
}