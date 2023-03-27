package game.datas.towerdatas.decorator.decorations;

import game.datas.towerdatas.TowerStats;
import game.datas.towerdatas.decorator.TowerStatsDecorator;

public class DamageUpgrade extends TowerStatsDecorator {
    public DamageUpgrade(TowerStats stats) {
        super(stats);
    }

    @Override
    public int attack(int lv) {
        return getStats().attack(lv) + (int) (getStats().attack(lv) * 0.1);
    }
}
