package edu.kis.powp.jobs2d.drivers.transformations;

public interface CoordsTransformation
{
    int transformXPoint(int x, int y);

    int transformYPoint(int x, int y);
}
