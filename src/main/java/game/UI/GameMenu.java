package game.UI;


import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.view.KeyView;
import com.almasb.fxgl.input.view.MouseButtonView;
import com.almasb.fxgl.scene.Scene;
import game.UI.components.CustomMenuButton;
import game.datas.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;
import static javafx.scene.input.KeyCode.*;

public class GameMenu extends FXGLMenu {

    private final VBox scoresRoot = new VBox(10);
    private final boolean isLoadedScore = false;

    public GameMenu() {
        super(MenuType.MAIN_MENU);
        var bg = new Rectangle(Config.SCREEN_W, Config.SCREEN_H);
        bg.setFill(Color.valueOf("#7985ab"));
        getContentRoot().getChildren().setAll(new Rectangle(getAppWidth(), getAppHeight()));

        var title = getUIFactoryService().newText(getSettings().getTitle(), Color.WHITE, 60);
        title.setStroke(Color.WHITESMOKE);
        title.setStrokeWidth(1.5);

        if (!FXGL.isMobile()) {
            title.setEffect(new Bloom(0.6));
        }
        centerTextBind(title, getAppWidth() / 2.0, 200);

        var version = getUIFactoryService().newText(getSettings().getVersion(), Color.WHITE, 22.0);
        centerTextBind(version, getAppWidth() / 2.0, 220);

        var menuBox = new VBox(
                5,
                new CustomMenuButton("New Game", () -> fireNewGame(), 200, 30),
                new CustomMenuButton("How to Play", () -> instructions(), 200, 30),
                new CustomMenuButton("Exit", () -> fireExit(), 200, 30)
        );


        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(getAppWidth() / 2.0 - 100);
        menuBox.setTranslateY(getAppHeight() / 2.0);

        // useful for checking if nodes are properly centered
        var centeringLine = new Line(getAppWidth() / 2.0, 0, getAppWidth() / 2.0, getAppHeight());
        centeringLine.setStroke(Color.WHITE);

        scoresRoot.setPadding(new Insets(10));
        scoresRoot.setAlignment(Pos.TOP_LEFT);
        

        getContentRoot().getChildren().addAll(bg, title, version, menuBox);
    }

    @Override
    public void onCreate() {
        if (isLoadedScore) {
        }
    }

    @Override
    public void onEnteredFrom(Scene prevState) {
    }


    private void instructions() {
        GridPane pane = new GridPane();
        if (!FXGL.isMobile()) {
            pane.setEffect(new DropShadow(5, 3.5, 3.5, Color.BLUE));
        }
        pane.setHgap(25);
        pane.setVgap(10);
        pane.addRow(0, getUIFactoryService().newText("Movement"), new HBox(4, new KeyView(Z), new KeyView(Q), new KeyView(S), new KeyView(D)));
        pane.addRow(1, getUIFactoryService().newText("Place tower / Show stats"), new MouseButtonView(MouseButton.PRIMARY));
        pane.addRow(2, getUIFactoryService().newText("Hide Panels"), new MouseButtonView(MouseButton.SECONDARY));

        getDialogService().showBox("How to Play", pane, getUIFactoryService().newButton("OK"));
    }
}