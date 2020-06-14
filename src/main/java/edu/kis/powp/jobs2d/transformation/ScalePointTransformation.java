package edu.kis.powp.jobs2d.transformation;

public class ScalePointTransformation implements PointTransformation{
    private double scaleX;
    private double scaleY;

    public ScalePointTransformation(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public int getTransformedX(int x, int y) {
        return (int) (x * scaleX);
    }

    @Override
    public int getTransformedY(int x, int y) {
        return (int) (y * scaleY);
    }
}
