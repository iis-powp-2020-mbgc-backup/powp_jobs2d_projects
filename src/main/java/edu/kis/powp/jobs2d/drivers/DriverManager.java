package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.extensions.ExtensionDriverComposite;
import edu.kis.powp.jobs2d.drivers.extensions.UsageMonitorExtension;
import edu.kis.powp.observer.Publisher;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {
    private ExtensionDriverComposite extensionDriver = new ExtensionDriverComposite();
    private Publisher changePublisher = new Publisher();

    /**
     * @param driver Set the driver as current.
     */
    public synchronized void setCurrentDriver(Job2dDriver driver) {
        extensionDriver.setDriver(driver);
        changePublisher.notifyObservers();
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }

    public synchronized ExtensionDriverComposite getExtensionDriver() {
        return extensionDriver;
    }
}
