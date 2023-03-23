package game.datas;

public class WaveData {
    private final boolean hasBoss;
    private final int enemyCount;
    private final int reward;

    public WaveData(int waveCount) {
        hasBoss = waveCount % 10 == 0;
        enemyCount = (int) (((waveCount + 1) / 2) + ((waveCount - 1) * waveCount * 0.1));
        reward = waveCount * 50;
    }

    public boolean isHasBoss() {
        return hasBoss;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public int getReward() {
        return reward;
    }
}
