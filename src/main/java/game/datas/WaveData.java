package game.datas;

public class WaveData {
    private final boolean hasBoss;
    private final int enemyCount;

    private final int hpPool;

    public WaveData(int waveCount) {
        hasBoss = waveCount % 10 == 0;
        enemyCount = (int) ((Math.log(waveCount) * 4) + 1);
        hpPool = (int) (Math.pow(waveCount, 3)*1.4) + 10 * waveCount;
    }

    public boolean hasBoss() {
        return hasBoss;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public int getHpPool() {
        return hpPool;
    }


}
