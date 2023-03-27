package game.datas.towerdatas.decorator;

import game.datas.towerdatas.TowerStats;

public abstract class TowerStatsDecorator extends TowerStats {

    private final TowerStats stats;

    public TowerStatsDecorator(TowerStats stats) {
        this.stats = stats;
    }

    public TowerStats getStats() {
        return stats;
    }
}
