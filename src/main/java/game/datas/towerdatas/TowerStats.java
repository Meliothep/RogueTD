package game.datas.towerdatas;

public abstract class TowerStats {
    public String description() {
        return "Basic stats";
    }

    public int attack(int lv) {
        return lv * 3 + 10;
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

    public double xpGain() {
        return 1;
    }
}
