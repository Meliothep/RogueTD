package game.datas.towerdatas;

public abstract class TowerStats {

    public int attack(int lv) {
        return lv * 3 + 5;
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
