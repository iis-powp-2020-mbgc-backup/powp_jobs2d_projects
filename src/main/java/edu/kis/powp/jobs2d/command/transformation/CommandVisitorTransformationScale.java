package edu.kis.powp.jobs2d.command.transformation;

public class CommandVisitorTransformationScale extends CommandVisitorTransformation {
    private final double scaleFactorX;
    private final double scaleFactorY;

    public CommandVisitorTransformationScale(double scaleFactorX, double scaleFactorY) {
        super();
        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
    }

    @Override
    int transformXPoint(int x, int y) {
        return (int) (x * this.scaleFactorX);
    }

    @Override
    int transformYPoint(int x, int y) {
        return (int) (y * this.scaleFactorY);
    }
}