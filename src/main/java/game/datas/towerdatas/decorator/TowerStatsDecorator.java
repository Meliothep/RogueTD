package game.datas.towerdatas.decorator;

import game.datas.towerdatas.TowerStats;

public abstract class TowerStatsDecorator extends TowerStats {

    private final TowerStats stats;

    public TowerStatsDecorator(TowerStats stats) {
        this.stats = stats;
    }

    @Override
    public int attack(int lv) {
        return stats.attack(lv);
    }

    @Override
    public int effect() {
        return stats.effect();
    }

    @Override
    public double range() {
        return stats.range();
    }

    @Override
    public double cooldown() {
        return stats.cooldown();
    }

    @Override
    public double xpGain() {
        return stats.xpGain();
    }

}
