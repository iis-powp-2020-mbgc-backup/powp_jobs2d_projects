package edu.kis.powp.jobs2d.drivers.transformations;

public class CoordsRotateTransformation implements CoordsTransformation
{
    private final double angle;

    public CoordsRotateTransformation(double angle) {
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
