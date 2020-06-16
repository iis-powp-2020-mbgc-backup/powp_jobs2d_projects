package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.Transformations.Transformation;
import edu.kis.powp.jobs2d.features.Transformations.TransformationDecorator;

public class TransformationAdapter implements Job2dDriver {
    private Job2dDriver driver;
    private String name;
    private Transformation transformator;

    public TransformationAdapter(Job2dDriver driver, String name) {
        this.driver = driver;
        this.transformator = new TransformationDecorator(name);
        this.name = name;
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



    @Override
    public String toString() {
        return "2d device simulator - " + name;
    }
}

