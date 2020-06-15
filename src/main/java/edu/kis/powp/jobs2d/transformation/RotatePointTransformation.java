package edu.kis.powp.jobs2d.transformation;

/**
 * Class used for point rotating.
 */
public class RotatePointTransformation implements PointTransformation {
    private double angle;

    /**
     * Creates a RotatePointTransformation.
     * @param angleInDegrees Angle in degrees by which the point should be rotated.
     */
    public RotatePointTransformation(double angleInDegrees) {
        angle = Math.toRadians(angleInDegrees);
    }

    /**
     * Get x coordinate after point rotation.
     * @param x Current x coordinate.
     * @param y Current y coordinate.
     * @return x coordinate for a point after rotating.
     */
    @Override
    public int getTransformedX(int x, int y) {
        return (int) (x * Math.cos(angle) - y * Math.sin(angle));
    }

    /**
     * Get y coordinate after point rotation.
     * @param x Current x coordinate.
     * @param y Current y coordinate.
     * @return y coordinate for a point after rotating.
     */
    @Override
    public int getTransformedY(int x, int y) {
        return (int) (x * Math.sin(angle) + y * Math.cos(angle));
    }
}
