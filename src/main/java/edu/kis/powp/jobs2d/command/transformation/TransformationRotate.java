package edu.kis.powp.jobs2d.command.transformation;

public class TransformationRotate implements Transformation {

    private final double angle;

    public TransformationRotate(double angle) {
        this.angle = Math.toRadians(angle);
    }

    public int transformXPoint(int x, int y) {
        return (int) (x * Math.cos(angle) - y * Math.sin(angle));
    }

    public int transformYPoint(int x, int y) {
        return (int) (x * Math.sin(angle) + y * Math.cos(angle));
    }
}
