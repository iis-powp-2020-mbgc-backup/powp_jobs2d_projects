package edu.kis.powp.jobs2d.drivers.transformation;

public class Translate implements Transformation {
    private final int x;
    private final int y;
    
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
