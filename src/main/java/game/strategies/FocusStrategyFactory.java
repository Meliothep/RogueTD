package game.strategies;

public class FocusStrategyFactory {
    public static FocusStrategy getStrategy(FocusStrategies name) {
        switch (name) {
            case Progress -> {
                return new FocusProgress();
            }
            case LeastHP -> {
                return new FocusLeastHP();
            }
            case MostHP -> {
                return new FocusMostHP();
            }
            default -> {
                return new FocusNearest();
            }
        }
    }
}
