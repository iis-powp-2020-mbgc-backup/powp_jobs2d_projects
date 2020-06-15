package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;


/**
 * Observer for a driver change event.
 */
public class DriverTitleBarChangeObserver implements Subscriber {

    /**
     * Update driver info.
     */
    public void update() {
        DriverFeature.updateDriverInfo();
    }

    /**
     * Returns a string representation of this observer.
     * @return Name of the observer.
     */
    public String toString() {
        return "Driver Title Bar Change Observer";
    }
}
