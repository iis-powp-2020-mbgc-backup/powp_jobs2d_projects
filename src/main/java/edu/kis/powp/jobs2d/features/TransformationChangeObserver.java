package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverTransformationDecorator;
import edu.kis.powp.jobs2d.features.Transformations.*;
import edu.kis.powp.observer.Subscriber;

import java.util.ArrayList;

public class TransformationChangeObserver implements Subscriber {
    ArrayList<String> driversNames = new ArrayList<>();
    @Override
    public void update() {
        String name = DriverFeature.getDriverManager().getCurrentDriver().toString().replace("2d device simulator -", "");
        if(!driversNames.contains(name)) {
            driversNames.add(name);
            DriverFeature.addDriver(name + "- scale 2x", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                    TransformationFactory.getTransformation(Transformations.SCALE2)));

            DriverFeature.addDriver(name + "- scale 0.5x", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                    TransformationFactory.getTransformation(Transformations.SCALE05)));

            DriverFeature.addDriver(name + "- rotate 30", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                    TransformationFactory.getTransformation(Transformations.ROTATE30)));

            DriverFeature.addDriver(name + "- flip horizontal", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                    TransformationFactory.getTransformation(Transformations.FLIPH)));

            DriverFeature.addDriver(name + "- flip vertical", new DriverTransformationDecorator(DriverFeature.getDriverManager().getCurrentDriver(),
                    TransformationFactory.getTransformation(Transformations.FLIPV)));
        }
    }

    public String toString(){
        return "Transformation Change Observer";
    }
}
