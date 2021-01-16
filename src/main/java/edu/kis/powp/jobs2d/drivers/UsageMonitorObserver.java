package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.extensions.UsageMonitorExtension;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class UsageMonitorObserver implements Subscriber {

    @Override public void update() {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        if (!(currentDriver instanceof UsageMonitorExtension)) {
            DriverFeature.getDriverManager().setCurrentDriver(new UsageMonitorExtension());
        }
    }
}
