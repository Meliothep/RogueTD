package game.datas.towerdatas;

import game.strategies.FocusStrategy;

public interface TowerData {
    int attack();

    int effect();

    double range();

    double cooldown();

    int xp();

    void gainXp(int q);

    FocusStrategy focusStrategy();

    void setFocusStrategy(FocusStrategy focusStrategy);

    int upgradeCost();
}
