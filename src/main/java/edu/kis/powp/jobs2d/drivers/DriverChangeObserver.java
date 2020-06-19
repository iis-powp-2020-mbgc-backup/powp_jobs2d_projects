package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeObserver implements Subscriber {

    private InkUsageDriverDecorator previousIUD;

    @Override public void update()
    {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        InkUsageDriverDecorator driver;

        if(previousIUD != null)
        {
            driver = new InkUsageDriverDecorator(currentDriver, previousIUD.getInkLimit(), previousIUD.getTotalUsage());
        }
        else
        {
            driver = new InkUsageDriverDecorator(currentDriver, 10000f);
        }

        DriverFeature.getDriverManager().setCurrentDriver(driver);
        previousIUD = driver;
    }
}
