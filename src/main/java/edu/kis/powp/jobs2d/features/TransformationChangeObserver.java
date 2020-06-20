package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverTransformationDecorator;
import edu.kis.powp.jobs2d.features.Transformations.*;
import edu.kis.powp.observer.Subscriber;

import java.util.ArrayList;

public class TransformationChangeObserver implements Subscriber {

    @Override
    public void update() {
        DriverFeature.addDriver("scale 2x", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.SCALE2)));

        DriverFeature.addDriver("scale 0.5x", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.SCALE05)));

        DriverFeature.addDriver("rotate 30", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.ROTATE30)));

        DriverFeature.addDriver("flip horizontal", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.FLIPH)));

        DriverFeature.addDriver("flip vertical", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.FLIPV)));
    }

    public String toString(){
        return "Transformation Change Observer";
    }
}
