package edu.kis.powp.jobs2d.features;

import edu.kis.powp.observer.Subscriber;

public class DriverFeatureChangeObserver implements Subscriber {

    public void update() {
        DriverFeature.updateDriverInfo();
        //DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
    }

    public String toString() {
        return "Driver Feature Change Observer";
    }
}