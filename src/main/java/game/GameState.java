package game;

import game.datas.Way;
import game.entities.tile.Tile;
import game.entities.tile.monument.Base;
import javafx.geometry.Point3D;

import java.util.*;

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

    public void initWays(Base base) {
        List<Point3D> list = new Stack<Point3D>();
        list.add(new Point3D(1.2, -0.3, 1.8));
        ways.put(base, new Way(list));
    }

    public Way getWay(Tile tile) {
        return ways.get(tile);
    }

    public void addWayPoints(Tile lastTile, Tile tile) {
        if (ways.containsKey(lastTile)) {
            Way way = ways.get(lastTile);
            ways.remove(lastTile);
            way.addWaypoints(tile.getWayPoints());
            ways.put(tile, way);
        } else {
            Way way = ways.get(lastTile);
            way.getWaypoints().addAll(tile.getWayPoints());
            ways.put(tile, way);
        }
    }

    public List<Point3D> getTileOrigins() {
        return tileOrigins;
    }

    public void addTileOrigin(Point3D point3D) {
        if (!tileOrigins.contains(point3D))
            tileOrigins.add(point3D);
    }

}
