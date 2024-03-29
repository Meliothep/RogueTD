package game.datas.towerdatas.decorator.decorations;

import game.datas.towerdatas.TowerStats;
import game.datas.towerdatas.decorator.TowerStatsDecorator;

public class XpGainUpgrade extends TowerStatsDecorator {
    public XpGainUpgrade(TowerStats stats) {
        super(stats);
    }

    @Override
    public String description() {
        return "+ 5%\n   XP";
    }

    @Override
    public double xpGain() {
        return super.xpGain() * 1.05;
    }
}