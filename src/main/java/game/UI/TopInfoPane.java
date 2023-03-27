package game.UI;


import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static game.datas.Config.SCREEN_W;
import static game.datas.Config.TOP_FONT;
import static game.datas.Vars.MONEY;
import static game.datas.Vars.PLAYER_HP;

public class TopInfoPane extends HBox {
    private final Label goldLabel;
    private final Label healthLabel;

    public TopInfoPane() {
        super(5);
        goldLabel = new Label();
        goldLabel.setFont(TOP_FONT);

        healthLabel = new Label();
        healthLabel.setFont(TOP_FONT);

        var labelHp = new Label("HEALTH : ");
        labelHp.setFont(TOP_FONT);

        var labelGold = new Label("GOLD : ");
        labelGold.setFont(TOP_FONT);

        var goldContainer = new HBox();
        goldContainer.setStyle("" +
                "-fx-background-radius: 10;\n" +
                "-fx-border-radius: 10;\n" +
                "-fx-border-width:2;\n" +
                "-fx-border-color: #fee2aa;\n" +
                "-fx-background-color:#d8b571;\n" +
                "-fx-padding: 5");
        goldContainer.setAlignment(Pos.CENTER);

        goldContainer.getChildren().add(labelGold);
        goldContainer.getChildren().add(goldLabel);

        var healthContainer = new HBox();
        healthContainer.setStyle("" +
                "-fx-background-radius: 10;\n" +
                "-fx-border-radius: 10;\n" +
                "-fx-border-width:2;\n" +
                "-fx-border-color: #fee2aa;\n" +
                "-fx-background-color:#d8b571;\n" +
                "-fx-padding: 5");
        healthContainer.setAlignment(Pos.CENTER);

        healthContainer.getChildren().add(labelHp);
        healthContainer.getChildren().add(healthLabel);

        getChildren().addAll(goldContainer, healthContainer);

        setAlignment(Pos.TOP_CENTER);
        setLayoutX(SCREEN_W / 2 - 100);
        setLayoutY(5);
        healthLabel.textProperty().bind(FXGL.getip(PLAYER_HP).asString());
        goldLabel.textProperty().bind(FXGL.getip(MONEY).asString());
    }

    private Label creatInfoLabel(String imageName) {
        Label label = new Label("0", FXGL.texture(imageName));
        label.setTextFill(Color.WHITE);
        label.setPrefWidth(80);
        label.setMaxWidth(80);
        label.setFont(Font.font(null, FontWeight.BOLD, 13));
        return label;
    }

}