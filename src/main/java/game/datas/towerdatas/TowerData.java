package game.datas.towerdatas;

public class TowerData {
    private final int multiplier;
    private int xp = 0;

    public TowerData(int height) {
        this.multiplier = height;
    }

    public int attack() {
        return (int) ((Math.sqrt(xp) * 2 + 5) * ((multiplier + 1) / 2));
    }

    public int effect() {
        return 1;
    }

    public double range() {
        return 2;
    }

    public double cooldown() {
        return 1.5;
    }

    public void gainXp() {
        xp += 2;
    }
}
