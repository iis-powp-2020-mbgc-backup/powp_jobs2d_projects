package edu.kis.powp.jobs2d.features.Transformations;

public class RotateTransformation implements Transformation {
    private final double angle;

    public RotateTransformation(double angle) {
        this.angle = Math.toRadians(angle);
    }

    @Override
    public int transformX(int x, int y) {
        return (int) (x * Math.cos(angle) - y * Math.sin(angle));
    }

    @Override
    public int transformY(int x, int y) {
        return (int) (x * Math.sin(angle) + y * Math.cos(angle));
    }
}
