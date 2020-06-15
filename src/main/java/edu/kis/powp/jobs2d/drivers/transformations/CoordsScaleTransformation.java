package edu.kis.powp.jobs2d.drivers.transformations;

public class CoordsScaleTransformation implements CoordsTransformation
{
    private final double scaleFactorX;
    private final double scaleFactorY;

    public CoordsScaleTransformation(double scaleFactorX, double scaleFactorY) {
        super();
        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
    }

    @Override
    public int transformXPoint(int x, int y) {
        return (int) (x * this.scaleFactorX);
    }

    @Override
    public int transformYPoint(int x, int y) {
        return (int) (y * this.scaleFactorY);
    }

    @Override
    public String toString()
    {
        return "Scale Transformation (" + scaleFactorX + "x, " + scaleFactorY + "y)";
    }
}
