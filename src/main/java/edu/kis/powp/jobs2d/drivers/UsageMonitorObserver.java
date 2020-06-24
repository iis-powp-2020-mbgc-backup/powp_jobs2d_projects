package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class UsageMonitorObserver implements Subscriber {

    @Override public void update() {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        if (!(currentDriver instanceof Job2dDriverDecorator)) {
            DriverFeature.getDriverManager().setCurrentDriver(new Job2dDriverDecorator(currentDriver));
        }
    }
}
