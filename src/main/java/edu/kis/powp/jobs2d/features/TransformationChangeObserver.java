package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.adapter.TransformationAdapter;
import edu.kis.powp.jobs2d.features.Transformations.*;
import edu.kis.powp.observer.Subscriber;

public class TransformationChangeObserver implements Subscriber {
    @Override
    public void update() {
        DriverFeature.addDriver("scale 2x", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.SCALE2)));

        DriverFeature.addDriver("scale 0.5x", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.SCALE05)));

        DriverFeature.addDriver("rotate 30", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.ROTATE30)));

        DriverFeature.addDriver("flip horizontal", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.FLIPH)));

        DriverFeature.addDriver("flip vertical", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(),
                TransformationFactory.getTransformation(Transformations.FLIPV)));
    }

    public String toString(){
        return "Transformation Change Observer";
    }
}
