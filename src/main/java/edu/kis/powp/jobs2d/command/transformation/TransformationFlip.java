package edu.kis.powp.jobs2d.command.transformation;

public class TransformationFlip implements Transformation {
    private final boolean isHorizontal;

    public TransformationFlip(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

    public int transformXPoint(int x, int y) {
        return isHorizontal ? -x : x;
    }

    public int transformYPoint(int x, int y) {
        return isHorizontal ? y : -y;
    }
}
