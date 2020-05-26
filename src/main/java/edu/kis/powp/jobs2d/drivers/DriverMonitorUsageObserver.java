package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.command.gui.MonitorUsageWindow;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverMonitorUsageObserver implements Subscriber {
    private final MonitorUsageWindow monitorUsageWindow;

    public DriverMonitorUsageObserver(MonitorUsageWindow monitorUsageWindow) {
        this.monitorUsageWindow = monitorUsageWindow;
    }

    @Override
    public void update() {
        monitorUsageWindow.flush(DriverFeature.getDriverManager().getCurrentDriver().toString());
    }
}
