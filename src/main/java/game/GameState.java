package game;

import game.entities.tile.Tile;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private static GameState instance;

    private final int waveCount = 0;
    private final List<Point3D> checkPoints = new ArrayList<>();
    private final List<Point3D> tileOrigins = new ArrayList<>();

    private Tile finalTile;

    private GameState() {
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void addCheckPoint(Point3D point3D) {
        if (!checkPoints.contains(point3D))
            checkPoints.add(point3D);
    }

    public void addTileOrigin(Point3D point3D) {
        if (!tileOrigins.contains(point3D))
            tileOrigins.add(point3D);
    }

}
