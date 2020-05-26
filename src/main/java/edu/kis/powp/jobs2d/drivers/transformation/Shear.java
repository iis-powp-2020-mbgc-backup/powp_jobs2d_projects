package edu.kis.powp.jobs2d.drivers.transformation;

public class Shear implements Transformation {
    private final double xFactor;
    private final double yFactor;
    
    public Shear(double xFactor, double yFactor) {
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }
    
    @Override
    public Point apply(Point point) {
        double x = point.getX() + point.getY() * xFactor;
        double y = point.getY() + point.getX() * yFactor;
        
        return new Point((int) x, (int) y);
    }
}
