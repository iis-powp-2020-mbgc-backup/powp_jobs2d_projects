package edu.kis.powp.jobs2d.drivers.transformation;

public class Rotate implements Transformation {
    private final double angle;
    
    public Rotate(double angle) {
        this.angle = angle;
    }
    
    @Override
    public Point apply(Point point) {
        double x = point.getX() * Math.cos(angle) - point.getY() * Math.sin(angle);
        double y = point.getY() * Math.cos(angle) + point.getX() * Math.sin(angle);
        
        return new Point((int) x, (int) y);
    }
}
