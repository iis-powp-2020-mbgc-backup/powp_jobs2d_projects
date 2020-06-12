package edu.kis.powp.jobs2d.transformation;

public class RotatePointTransformation implements PointTransformation {
    private double angle;

    public RotatePointTransformation(double angleInDegrees) {
        angle = Math.toRadians(angleInDegrees);
    }

    @Override
    public int getTransformedX(int x, int y) {
        return (int) (x * Math.cos(angle) - y * Math.sin(angle));
    }

    @Override
    public int getTransformedY(int x, int y) {
        return (int) (x * Math.sin(angle) + y * Math.cos(angle));
    }
}
