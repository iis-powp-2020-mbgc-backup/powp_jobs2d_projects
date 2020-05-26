package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;


public class DriverChangeObserver implements Subscriber {

    public void update() {
        DriverFeature.updateDriverInfo();
    }

    public String toString() {
        return "Driver Change Observer";
    }
}
