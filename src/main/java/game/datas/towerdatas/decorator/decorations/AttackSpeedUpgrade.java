package game.datas.towerdatas.decorator.decorations;

import game.datas.towerdatas.TowerStats;
import game.datas.towerdatas.decorator.TowerStatsDecorator;

public class AttackSpeedUpgrade extends TowerStatsDecorator {
    public AttackSpeedUpgrade(TowerStats stats) {
        super(stats);
    }

    @Override
    public String description() {
        return "+ 5%\n  CDR";
    }

    @Override
    public double cooldown() {
        return super.cooldown() * 0.95;
    }
}
