package game;

import game.datas.Way;
import game.entities.tile.Tile;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
    private static GameState instance;

    private final int waveCount = 0;
    private final List<Point3D> tileOrigins = new ArrayList<>();

    private final Map<Tile, Way> ways = new HashMap<>();

    private GameState() {
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public List<Point3D> getTileOrigins() {
        return tileOrigins;
    }

    public void addTileOrigin(Point3D point3D) {
        if (!tileOrigins.contains(point3D))
            tileOrigins.add(point3D);
    }

}
