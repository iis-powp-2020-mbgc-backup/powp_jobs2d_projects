package edu.kis.powp.jobs2d.drivers.transformation;

import java.util.ArrayList;
import edu.kis.powp.jobs2d.Job2dDriver;

public class TransformationDriver implements Job2dDriver {
    private final Job2dDriver driver;
    private final ArrayList<Transformation> allTransformations = new ArrayList<>();
    
    public TransformationDriver(Job2dDriver job2dDriver) {
        driver = job2dDriver;
    }
    
    private Point applyAllTransformations(Point point) {
        for (Transformation transformation : allTransformations) {
            point = transformation.apply(point);
        }
        
        return point;
    }
    
    public void addTransformation(Transformation transformation) {
        allTransformations.add(transformation);
    }
    
    @Override
    public void setPosition(int x, int y) {
        Point point = applyAllTransformations(new Point(x, y));
        driver.setPosition(point.getX(), point.getY());
    }

    @Override
    public void operateTo(int x, int y) {
        Point point = applyAllTransformations(new Point(x, y));
        driver.operateTo(point.getX(), point.getY());
    }
    
    @Override
    public String toString() {
        return "Transformation Driver";
    }
}
