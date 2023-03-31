package game.UI;

import com.almasb.fxgl.dsl.FXGL;
import game.datas.Config;
import game.datas.towerdatas.TowerData;
import game.strategies.FocusStrategies;
import game.strategies.FocusStrategyFactory;
import game.utils.Utils;
import game.utils.observer.NormalTowerObserver;
import game.utils.observer.ObservableTowerData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;

import static game.datas.Config.XP_COST;
import static game.datas.Vars.MONEY;


public class TowerDetailPane extends HBox implements NormalTowerObserver {

    private final VBox rightContainer = new VBox();
    private final VBox leftContainer = new VBox();
    private final FocusStrategyFactory factory = new FocusStrategyFactory();
    private ObservableTowerData data;
    private Label levelTextLabel;
    private Label levelCostLabel;
    private Label currentStrategy;
    private Label cooldownValue;
    private Label rangeValue;
    private Label attackValue;

    public TowerDetailPane() {
        super(3);

        upgradeBox();

        strategyBox();

        rightContainer.setSpacing(5);
        getChildren().add(rightContainer);

        statsBox();

        leftContainer.setSpacing(5);
        getChildren().add(leftContainer);
    }

    private void statsBox() {
        var statsBox = new VBox();

        var title = new Label("Stats");
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(150);
        title.setFont(Config.DETAIL_TITLE_FONT);

        var container = new HBox();

        statsBox.setStyle("-fx-background-size: 150 50;\n" +
                "-fx-background-radius: 10;\n" +
                "-fx-border-radius: 10;\n" +
                "-fx-border-width:2;\n" +
                "-fx-border-color: #fee2aa;\n" +
                "-fx-background-color:#d8b571;\n");

        var statNames = new VBox();
        statNames.setStyle("-fx-padding: 5;");

        var attack = new Label("attack");
        attack.setFont(Config.DETAIL_FONT);
        attack.setStyle("-fx-padding: 1;");

        var range = new Label("range");
        range.setFont(Config.DETAIL_FONT);
        range.setStyle("-fx-padding: 1;");

        var cooldown = new Label("cooldown");
        cooldown.setFont(Config.DETAIL_FONT);
        cooldown.setStyle("-fx-padding: 1;");

        statNames.getChildren().add(attack);
        statNames.getChildren().add(range);
        statNames.getChildren().add(cooldown);

        var values = new VBox();
        values.setStyle("-fx-padding: 5;");

        attackValue = new Label("attack");
        attackValue.setFont(Config.DETAIL_FONT);
        attackValue.setStyle("-fx-padding: 1;");

        rangeValue = new Label("range");
        rangeValue.setFont(Config.DETAIL_FONT);
        rangeValue.setStyle("-fx-padding: 1;");

        cooldownValue = new Label("cooldown");
        cooldownValue.setFont(Config.DETAIL_FONT);
        cooldownValue.setStyle("-fx-padding: 1;");

        values.getChildren().add(attackValue);
        values.getChildren().add(rangeValue);
        values.getChildren().add(cooldownValue);

        container.getChildren().add(statNames);
        container.getChildren().add(values);

        statsBox.getChildren().add(title);
        statsBox.getChildren().add(container);

        leftContainer.getChildren().add(statsBox);

    }

    private void upgradeBox() {
        var detailVbox = new VBox();

        var title = new Label("Upgrades");
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(150);
        title.setFont(Config.DETAIL_TITLE_FONT);

        var container = new HBox();

        detailVbox.setStyle("-fx-background-size: 150 50;\n" +
                "-fx-background-radius: 10;\n" +
                "-fx-border-radius: 10;\n" +
                "-fx-border-width:2;\n" +
                "-fx-border-color: #fee2aa;\n" +
                "-fx-background-color:#d8b571;\n");

        var statNames = new VBox();
        statNames.setStyle("-fx-padding: 5;");

        var upgradeLabel = new Label("Upgrade");
        upgradeLabel.setFont(Config.DETAIL_FONT);
        upgradeLabel.setStyle("-fx-padding: 1;");

        levelTextLabel = new Label("level");
        levelTextLabel.setFont(Config.DETAIL_FONT);
        levelTextLabel.setStyle("-fx-padding: 4;");

        statNames.getChildren().add(upgradeLabel);
        statNames.getChildren().add(levelTextLabel);

        var costs = new VBox();
        costs.setStyle("-fx-padding: 5;");

        var costLabel = new Label("cost");
        costLabel.setFont(Config.DETAIL_FONT);
        costs.setStyle("-fx-padding: 6;");

        levelCostLabel = new Label("0");
        levelCostLabel.setFont(Config.DETAIL_FONT);
        levelCostLabel.setStyle("-fx-padding: 4;");

        costs.getChildren().add(costLabel);
        costs.getChildren().add(levelCostLabel);

        var upgrades = new VBox();
        upgrades.setStyle("-fx-padding: 5;");

        var levelUpLabel = new Label(" UP");
        levelUpLabel.setFont(Config.DETAIL_FONT);
        levelUpLabel.setStyle("-fx-padding: 1;");

        var levelupBtn = new Button("+");
        levelupBtn.setFont(Config.DETAIL_FONT);
        levelupBtn.setStyle("-fx-background-radius: 3;\n" +
                "-fx-background-color:#fee2aa");

        levelupBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (FXGL.geti(MONEY) > ((TowerData) data).upgradeCost()) {
                    FXGL.inc(MONEY, -((TowerData) data).upgradeCost());
                    ((TowerData) data).gainXp(((TowerData) data).upgradeCost() / XP_COST);
                }
            }
        });

        upgrades.getChildren().add(levelUpLabel);
        upgrades.getChildren().add(levelupBtn);


        container.getChildren().add(statNames);
        container.getChildren().add(costs);
        container.getChildren().add(upgrades);

        detailVbox.getChildren().add(title);
        detailVbox.getChildren().add(container);

        rightContainer.getChildren().add(detailVbox);
    }

    private void strategyBox() {

        var strategyBox = new VBox();
        strategyBox.setStyle("-fx-background-size: 150 50;\n" +
                "-fx-background-radius: 10;\n" +
                "-fx-border-radius: 10;\n" +
                "-fx-border-width:2;\n" +
                "-fx-border-color: #fee2aa;\n" +
                "-fx-background-color:#d8b571;\n");

        var title = new Label("Target priority");
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(150);
        title.setFont(Config.DETAIL_TITLE_FONT);
        title.setStyle("-fx-padding: 2;");

        var controlBox = new HBox();
        controlBox.setAlignment(Pos.CENTER);

        var leftButtonsBox = new VBox();
        leftButtonsBox.setStyle("-fx-padding: 5;");

        var strategyBtnLeft = new Button("<");
        strategyBtnLeft.setFont(Config.DETAIL_FONT);
        strategyBtnLeft.setStyle("-fx-background-radius: 3;\n" +
                "-fx-background-color:#fee2aa;\n");

        strategyBtnLeft.setOnAction(actionEvent -> {
            var list = Arrays.stream(FocusStrategies.values()).toList();
            var index = list.indexOf(((TowerData) (data)).focusStrategy().name()) - 1;
            var newStrategy = index >= 0 ? list.get(index % 4) : list.get(4 + index);
            ((TowerData) (data)).setFocusStrategy(factory.getStrategy(newStrategy));
            update(((TowerData) (data)));
        });

        leftButtonsBox.getChildren().add(strategyBtnLeft);

        var labelsBox = new VBox();
        labelsBox.setStyle("-fx-padding: 5;");

        currentStrategy = new Label("nearest");
        currentStrategy.setFont(Config.DETAIL_FONT);
        currentStrategy.setStyle("-fx-padding: 3");

        labelsBox.getChildren().add(currentStrategy);

        var rightButtonsBox = new VBox();
        rightButtonsBox.setStyle("-fx-padding: 5;");

        var strategyBtnRight = new Button(">");
        strategyBtnRight.setFont(Config.DETAIL_FONT);
        strategyBtnRight.setStyle("-fx-background-radius: 3;\n" +
                "-fx-background-color:#fee2aa;\n");

        strategyBtnRight.setOnAction(actionEvent -> {
            var list = Arrays.stream(FocusStrategies.values()).toList();
            var index = list.indexOf(((TowerData) data).focusStrategy().name()) + 1;
            var newStrategy = list.get(index % 4);
            ((TowerData) data).setFocusStrategy(factory.getStrategy(newStrategy));
            update(((TowerData) data));
        });

        rightButtonsBox.getChildren().add(strategyBtnRight);

        controlBox.getChildren().add(leftButtonsBox);
        controlBox.getChildren().add(labelsBox);
        controlBox.getChildren().add(rightButtonsBox);


        strategyBox.getChildren().add(title);
        strategyBox.getChildren().add(controlBox);

        rightContainer.getChildren().add(strategyBox);
    }

    public ObservableTowerData getData() {
        return data;
    }

    public void setData(ObservableTowerData data) {
        if (this.data != null)
            this.data.removeObserver(this);
        this.data = data;
        data.addObserver(this);
        update(((TowerData) (data)));
    }

    public void update(TowerData data) {
        levelTextLabel.setText("level " + (int) Math.sqrt(data.xp()));
        levelCostLabel.setText(String.valueOf(data.upgradeCost()));
        currentStrategy.setText(data.focusStrategy().name().name());
        attackValue.setText(String.valueOf(Utils.round(data.attack(), 2)));
        rangeValue.setText(String.valueOf(Utils.round(data.range(), 2)));
        cooldownValue.setText(String.valueOf(Utils.round(data.cooldown(), 2)));
    }
}
