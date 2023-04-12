package game.UI.components;

import com.almasb.fxgl.ui.FontType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;

public class CustomMenuButton extends Parent {
    public CustomMenuButton(String name, Runnable action, float w, float h) {
        var box = new HBox();
        box.setPrefSize(w, h);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-radius: 15;\n" +
                "-fx-border-radius: 15;\n" +
                "-fx-border-width:2;\n" +
                "-fx-border-color: #fee2aa;\n" +
                "-fx-background-color:#d8b571;\n" +
                "-fx-padding: 7;");

        var text = getUIFactoryService().newText(name, Color.valueOf("#fee2aa"), FontType.UI, 20);
        text.setStrokeWidth(1.5);
        text.strokeProperty().bind(text.fillProperty());

        text.fillProperty().bind(
                Bindings.when(hoverProperty())
                        .then(Color.valueOf("#000000"))
                        .otherwise(Color.WHITE)
        );

        setOnMouseClicked(e -> action.run());

        setPickOnBounds(true);

        box.getChildren().add(text);

        getChildren().add(box);
    }
}