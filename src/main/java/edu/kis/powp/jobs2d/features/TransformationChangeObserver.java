package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.adapter.TransformationAdapter;
import edu.kis.powp.jobs2d.features.Transformations.FlipTransformation;
import edu.kis.powp.jobs2d.features.Transformations.RotateTransformation;
import edu.kis.powp.jobs2d.features.Transformations.ScaleTransformation;
import edu.kis.powp.observer.Subscriber;

public class TransformationChangeObserver implements Subscriber {
    @Override
    public void update() {
        DriverFeature.addDriver("scale 2x", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new ScaleTransformation(2)));
        DriverFeature.addDriver("scale 0.5x", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new ScaleTransformation(0.5)));
        DriverFeature.addDriver("rotate 30", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new RotateTransformation(30)));
        DriverFeature.addDriver("flip horizontal", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new FlipTransformation(false)));
        DriverFeature.addDriver("flip vertical", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new FlipTransformation(true)));
    }

    public String toString(){
        return "Transformation Change Observer";
    }
}
