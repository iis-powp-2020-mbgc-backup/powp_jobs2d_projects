package edu.kis.powp.jobs2d.drivers.transformation;

/**
 * Scale transformation
 */
public class Scale implements Transformation {
    private final double xFactor;
    private final double yFactor;
    
    /**
     * Defines scale transformation by X and Y axis
     * 
     * @param factor - scale factor for both axis
     */
    public Scale(double factor) {
        this(factor, factor);
    }
    
    /**
     * Defines scale transformation by X and Y axis
     * 
     * @param xFactor - scale factor for X axis
     * @param yFactor - scale factor for Y axis
     */
    public Scale(double xFactor, double yFactor) {
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }
    
    @Override
    public Point apply(Point point) {
        double x = point.getX() * xFactor;
        double y = point.getY() * yFactor;
        
        return new Point((int) x, (int) y);
    }
}
