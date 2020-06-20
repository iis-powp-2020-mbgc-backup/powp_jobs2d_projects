package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.Transformations.Transformation;

public class DriverTransformationDecorator implements Job2dDriver {
    private Job2dDriver driver;
    private Transformation transformator;

    public DriverTransformationDecorator(Job2dDriver driver, Transformation transformation) {
        this.driver = driver;
        this.transformator = transformation;
    }

    @Override
    public void setPosition(int x, int y) {
        driver.setPosition(
                transformator.transformX(x, y), transformator.transformY(x, y));
    }

    @Override
    public void operateTo(int x, int y) {
        driver.operateTo(
                transformator.transformX(x, y), transformator.transformY(x, y));
    }
}

