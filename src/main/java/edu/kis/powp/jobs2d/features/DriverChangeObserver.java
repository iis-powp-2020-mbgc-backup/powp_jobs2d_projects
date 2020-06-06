package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.MacroDriver;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeObserver implements Subscriber {

    @Override
    public void update() {
        Job2dDriver job2dDriver = DriverFeature.getDriverManager().getCurrentDriver();
        if (!(job2dDriver instanceof MacroDriver))
            MacroFeature.getMacroDriver().setCoreJob2dDriver(job2dDriver);
    }
}
