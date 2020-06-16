package edu.kis.powp.jobs2d.transformation;

/**
 * Class used for point scaling.
 */
public class ScalePointTransformation implements PointTransformation{
    private double scaleX;
    private double scaleY;

    /**
     * Creates a ScalePointTransformation.
     * @param scaleX Parameter by which point should be scaled horizontally.
     * @param scaleY Parameter by which point should be scaled vertically.
     */
    public ScalePointTransformation(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * Get x coordinate after point scaling.
     * @param x Current x coordinate.
     * @param y Current y coordinate.
     * @return x coordinate for a point after scaling.
     */
    @Override
    public int getTransformedX(int x, int y) {
        return (int) (x * scaleX);
    }

    /**
     * Get y coordinate after point scaling.
     * @param x Current x coordinate.
     * @param y Current y coordinate.
     * @return y coordinate for a point after scaling.
     */
    @Override
    public int getTransformedY(int x, int y) {
        return (int) (y * scaleY);
    }
}
