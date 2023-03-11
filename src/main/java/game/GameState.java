package game;

import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private static GameState instance;

    private final int waveCount = 0;
    private final List<Point3D> checkPoints = new ArrayList<>();

    private GameState() {
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }
}
