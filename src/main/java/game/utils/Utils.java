package game.utils;

import javafx.geometry.Point3D;

import java.util.Random;

public class Utils {
    private Utils() {
    }

    public static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public static int randomIntBetween(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static Point3D rotatePoint(Point3D point3D, double angle) {
        double s = Math.sin(Math.toRadians(angle));
        double c = Math.cos(Math.toRadians(angle));

        // translate point back to origin:
        double currentX = point3D.getX() - 1.2;
        double currentZ = point3D.getZ() - 1.2;

        // rotate point
        double xnew = currentX * c - currentZ * s;
        double ynew = currentX * s + currentZ * c;
        return new Point3D(round(xnew + 1.2, 1), 0, round(ynew + 1.2, 1));
    }

}
