package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.gui.UsageMonitorWindow;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverUsageMonitorObserver implements Subscriber {
    private final UsageMonitorWindow usageMonitorWindow;

    public DriverUsageMonitorObserver(UsageMonitorWindow usageMonitorWindow) {
        this.usageMonitorWindow = usageMonitorWindow;
    }

    @Override
    public void update() {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        usageMonitorWindow.flush(currentDriver.toString());
        if (!(currentDriver instanceof DriverUsageMonitor)) {
            DriverFeature.getDriverManager().setCurrentDriver(new DriverUsageMonitor(currentDriver));
        }
    }
}
