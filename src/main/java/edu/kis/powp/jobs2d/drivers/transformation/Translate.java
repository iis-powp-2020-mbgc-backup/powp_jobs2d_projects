package edu.kis.powp.jobs2d.drivers.transformation;

/**
 * Translate transformation
 */
public class Translate implements Transformation {
    private final int x;
    private final int y;
    
    /**
     * Defines translate transformation by X and Y axis
     * 
     * @param x - translate distance in units for X axis
     * @param y - translate distance in units for Y axis
     */
    public Translate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public Point apply(Point point) {
        int newX = point.getX() + x;
        int newY = point.getY() + y;
        
        return new Point(newX, newY);
    }
}
