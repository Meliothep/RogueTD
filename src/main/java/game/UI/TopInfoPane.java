package game.UI;


import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static game.datas.Config.FONT;
import static game.datas.Vars.MONEY;
import static game.datas.Vars.PLAYER_HP;

public class TopInfoPane extends HBox {
    private final Label goldLabel;
    private final Label healthLabel;

    public TopInfoPane() {
        super(5);
        goldLabel = new Label();
        goldLabel.setFont(FONT);
        healthLabel = new Label();
        healthLabel.setFont(FONT);

        var labelHp = new Label("   HEALTH : ");
        labelHp.setFont(FONT);

        var labelGold = new Label("GOLD : ");
        labelGold.setFont(FONT);

        getChildren().addAll(labelGold, goldLabel, labelHp, healthLabel);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(5, 0, 0, 0));
        setPrefSize(448, 48);

        setLayoutX(351);
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