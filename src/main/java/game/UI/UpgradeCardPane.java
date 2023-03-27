package game.UI;

import com.almasb.fxgl.dsl.FXGL;
import game.GameState;
import game.RogueTD;
import game.datas.towerdatas.TowerStats;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import static game.datas.Config.DETAIL_TITLE_FONT;

public class UpgradeCardPane extends Button {
    private TowerStats value;

    public UpgradeCardPane() {

        setPrefSize(80, 100);
        setMaxHeight(100);

        setStyle("-fx-background-size: 75 100;\n" +
                "-fx-background-radius: 10;\n" +
                "-fx-border-radius: 10;\n" +
                "-fx-border-width:2;\n" +
                "-fx-border-color: #fee2aa;\n" +
                "-fx-background-color:#d8b571;\n" +
                "-fx-alignment: center;\n");

        setAlignment(Pos.CENTER);
        setFont(DETAIL_TITLE_FONT);
        setOnAction(actionEvent -> {
            var gamestate = GameState.getInstance();
            gamestate.setNormalTS(value);
            FXGL.<RogueTD>getAppCast().onUpgradeSelected();
        });
    }

    public void setValue(TowerStats value) {
        this.value = value;
        setText(value.description());
    }
}
