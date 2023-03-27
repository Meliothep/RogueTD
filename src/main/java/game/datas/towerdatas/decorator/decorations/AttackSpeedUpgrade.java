package game.datas.towerdatas.decorator.decorations;

import game.datas.towerdatas.TowerStats;
import game.datas.towerdatas.decorator.TowerStatsDecorator;

public class AttackSpeedUpgrade extends TowerStatsDecorator {
    public AttackSpeedUpgrade(TowerStats stats) {
        super(stats);
    }

    @Override
    public double cooldown() {
        return getStats().cooldown() * 0.9;
    }
}
