package game.datas.towerdatas.decorator.decorations;

import game.datas.towerdatas.TowerStats;
import game.datas.towerdatas.decorator.TowerStatsDecorator;

public class RangeUpgrade extends TowerStatsDecorator {
    public RangeUpgrade(TowerStats stats) {
        super(stats);
    }

    @Override
    public String description() {
        return "+ 5%\nRANGE";
    }


    @Override
    public double range() {
        return super.range() * 1.05;
    }
}