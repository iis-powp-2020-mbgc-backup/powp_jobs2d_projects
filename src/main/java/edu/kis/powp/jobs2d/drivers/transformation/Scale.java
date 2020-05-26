package edu.kis.powp.jobs2d.drivers.transformation;

public class Scale implements Transformation {
    private final double xFactor;
    private final double yFactor;
    
    public Scale(double factor) {
        this(factor, factor);
    }
    
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
