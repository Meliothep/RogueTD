package game.UI;

import game.GameState;
import game.UI.components.UpgradeCardPane;
import game.datas.towerdatas.decorator.TowerDecorationFactory;
import game.datas.towerdatas.decorator.TowerDecorations;
import javafx.scene.layout.HBox;

import static game.datas.Config.SCREEN_H;
import static game.datas.Config.SCREEN_W;
import static game.utils.Utils.randomIntBetween;

public class CardSelectionPane extends HBox {

    private final UpgradeCardPane card1 = new UpgradeCardPane();
    private final UpgradeCardPane card2 = new UpgradeCardPane();
    private final UpgradeCardPane card3 = new UpgradeCardPane();

    private final TowerDecorationFactory factory = new TowerDecorationFactory();

    public CardSelectionPane() {
        super(3);
        setPrefSize(SCREEN_W + 50, SCREEN_H);
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.2);" +
                "-fx-alignment: center");

        getChildren().add(card1);
        getChildren().add(card2);
        getChildren().add(card3);
        changeValues();
    }

    public void changeValues() {
        asignRandom(card1);
        asignRandom(card2);
        asignRandom(card3);
    }

    private void asignRandom(UpgradeCardPane card) {
        var towerDecorations = TowerDecorations.values();
        card.setValue(factory.getDecoration(
                towerDecorations[randomIntBetween(0, towerDecorations.length - 1)],
                GameState.getInstance().getNormalTS()));
    }
}
