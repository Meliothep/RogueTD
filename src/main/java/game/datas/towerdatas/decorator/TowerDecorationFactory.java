package game.datas.towerdatas.decorator;

import game.datas.towerdatas.TowerStats;
import game.datas.towerdatas.decorator.decorations.AttackSpeedUpgrade;
import game.datas.towerdatas.decorator.decorations.DamageUpgrade;
import game.datas.towerdatas.decorator.decorations.RangeUpgrade;
import game.datas.towerdatas.decorator.decorations.XpGainUpgrade;

public class TowerDecorationFactory {
    public TowerStats getDecoration(TowerDecorations name, TowerStats current) {
        switch (name) {
            case ATK -> {
                return new DamageUpgrade(current);
            }
            case ATK_SPEED -> {
                return new AttackSpeedUpgrade(current);
            }
            case RANGE -> {
                return new RangeUpgrade(current);
            }
            default -> {
                return new XpGainUpgrade(current);
            }
        }
    }
}
