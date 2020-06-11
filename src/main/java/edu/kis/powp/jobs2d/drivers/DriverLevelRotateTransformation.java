package edu.kis.powp.jobs2d.drivers;

public class DriverLevelRotateTransformation implements DriverLevelTransformation {
    private final double angle;

    public DriverLevelRotateTransformation(double angle) {
        super();
        this.angle = Math.toRadians(angle);
    }

    @Override
    public int transformXPoint(int x, int y) {
        return (int) (x * Math.cos(angle) - y * Math.sin(angle));
    }

    @Override
    public int transformYPoint(int x, int y) {
        return (int) (x * Math.sin(angle) + y * Math.cos(angle));
    }
}
