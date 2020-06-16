package edu.kis.powp.jobs2d.transformation;

/**
 * Interface used for point transformation.
 */
public interface PointTransformation {
    /**
     * Get x coordinate after point transformation.
     * @param x Current x coordinate.
     * @param y Current y coordinate.
     * @return x coordinate for a point after transformation.
     */
    int getTransformedX(int x, int y);

    /**
     * Get y coordinate after point transformation.
     * @param x Current x coordinate.
     * @param y Current y coordinate.
     * @return y coordinate for a point after transformation.
     */
    int getTransformedY(int x, int y);
}
