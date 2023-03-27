package game.datas.towerdatas;

import game.GameState;
import game.UI.TowerDetailPane;
import game.strategies.FocusStrategy;
import game.utils.observer.Observable;

import static game.datas.Config.XP_COST;

public class NormalTowerData extends Observable {

    private final int multiplier;
    private int xp = 0;
    private FocusStrategy focusStrategy;
    private TowerDetailPane pane;

    public NormalTowerData(int height, FocusStrategy strategy) {
        this.multiplier = height;
        this.focusStrategy = strategy;
    }

    public int attack() {
        return GameState.getInstance().getNormalTS().attack((int) Math.sqrt(xp)) * ((multiplier + 1) / 2);
    }

    public int effect() {
        return 1;
    }

    public double range() {
        return GameState.getInstance().getNormalTS().range();
    }

    public double cooldown() {
        return GameState.getInstance().getNormalTS().cooldown();
    }

    public int xp() {
        return xp;
    }

    public FocusStrategy getFocusStrategy() {
        return focusStrategy;
    }

    public void setFocusStrategy(FocusStrategy focusStrategy) {
        this.focusStrategy = focusStrategy;
    }

    public int upgradeCost() {
        return ((int) Math.pow((int) (Math.sqrt(xp)) + 1, 2) - xp) * XP_COST;
    }

    public void gainXp(int q) {
        xp += q * GameState.getInstance().getNormalTS().xpGain();
        super.notifyObs();
    }
}
