package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeObserver implements Subscriber {

    @Override
    public void update() {
        if (MacroFeature.isEnabled() && !(DriverFeature.getDriverManager().getCurrentDriver() instanceof DriverComposite)) {
            DriverManager driverManager = DriverFeature.getDriverManager();
            DriverCompositeFeature.setDrawingDriver(driverManager.getCurrentDriver());
            driverManager.setCurrentDriver(DriverCompositeFeature.getDriverComposite());
        }
    }
}
