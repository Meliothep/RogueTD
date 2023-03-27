package game.datas.towerdatas.decorator.decorations;

import game.datas.towerdatas.TowerStats;
import game.datas.towerdatas.decorator.TowerStatsDecorator;

public class DamageUpgrade extends TowerStatsDecorator {
    public DamageUpgrade(TowerStats stats) {
        super(stats);
    }

    @Override
    public String description() {
        return "+ 10% \nDAMAGE";
    }

    @Override
    public int attack(int lv) {
        return super.attack(lv) + (int) (super.attack(lv) * 0.1);
    }
}
