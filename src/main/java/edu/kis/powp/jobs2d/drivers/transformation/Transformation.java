package edu.kis.powp.jobs2d.drivers.transformation;

/**
 * Interface for all transformations
 */
public interface Transformation {
	/**
	 * Applies transformation for specified point
	 * 
	 * @param point - source point to apply transformation
	 * @return point after transformation
	 */
    public Point apply(Point point);
}
