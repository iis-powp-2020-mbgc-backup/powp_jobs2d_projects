package edu.kis.powp.jobs2d.drivers.transformations;

public class CoordsFlipTransformation implements CoordsTransformation
{

    private final boolean isHorizontal;

    public CoordsFlipTransformation(boolean isHorizontal) {
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

    @Override
    public String toString()
    {
        return "Flip Transformation";
    }
}
