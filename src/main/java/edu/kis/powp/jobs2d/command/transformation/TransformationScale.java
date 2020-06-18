package edu.kis.powp.jobs2d.command.transformation;

public class TransformationScale implements Transformation {

    private final double scaleFactorX;
    private final double scaleFactorY;

    public TransformationScale(double scaleFactorX, double scaleFactorY) {
        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
    }

    public int transformXPoint(int x, int y) {
        return (int) (x * this.scaleFactorX);
    }

    public int transformYPoint(int x, int y) {
        return (int) (y * this.scaleFactorY);
    }
}
