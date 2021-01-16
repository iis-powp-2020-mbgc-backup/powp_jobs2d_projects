package edu.kis.powp.jobs2d.drivers.transformation;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.extensions.ExtensionDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;

/**
 * Transformation driver
 * Design pattern: decorator-like
 * Basically works like decorator pattern,
 * but has an additional method (addTransformation)
 */
public class TransformationDriver implements ExtensionDriver {
    private Job2dDriver driver;
    private final List<Transformation> allTransformations = new ArrayList<>();

    public TransformationDriver() {
    }
    
    private Point applyAllTransformations(Point point) {
        for (Transformation transformation : allTransformations) {
            point = transformation.apply(point);
        }
        
        return point;
    }
    
    /**
     * Adds new transformation. Oder of adding matters.
     * First added transformation will be applied first
     * 
     * @param transformation - transformation to add
     */
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

    @Override
    public void setDriver(Job2dDriver driver) {
        this.driver = driver;
    }
}
