package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class InkUsageObserver implements Subscriber {

    @Override public void update()
    {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        DriverFeature.getDriverManager().setCurrentDriver(new InkUsageDriver(currentDriver, 10000f));
    }
}
