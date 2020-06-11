package edu.kis.powp.jobs2d.drivers.transformations;

public class DriverLevelFlipTransformation implements DriverLevelTransformation {

    private final boolean isHorizontal;

    public DriverLevelFlipTransformation(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

    @Override
    public int transformXPoint(int x, int y) {
        return isHorizontal ? -x : x;
    }

    @Override
    public int transformYPoint(int x, int y) {
        return isHorizontal ? y : -y;
    }
}
