package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class InkUsageMethodObserver implements Subscriber {
    private InkUsageDriverDecorator previousIUD;

    public InkUsageMethodObserver(double inkLimit){
        previousIUD = new InkUsageDriverDecorator(DriverFeature.getDriverManager().getCurrentDriver(), inkLimit);
    }

    @Override public void update()
    {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        InkUsageDriverDecorator driver = new InkUsageDriverDecorator(currentDriver, previousIUD.getInkLimit(),
                previousIUD.getTotalUsage(), previousIUD.getMaxInkLimit());

        DriverFeature.getDriverManager().setCurrentDriver(driver);
        previousIUD = driver;
    }
}
