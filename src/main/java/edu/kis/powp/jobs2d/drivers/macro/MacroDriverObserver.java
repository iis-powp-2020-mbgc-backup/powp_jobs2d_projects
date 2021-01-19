package edu.kis.powp.jobs2d.drivers.macro;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;
import edu.kis.powp.observer.Subscriber;

public class MacroDriverObserver implements Subscriber {

    @Override
    public void update() {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        if (!(currentDriver instanceof MacroDriverDecorator) && MacroFeature.getMacroDriverDecorator().isFlagActive()) {
            MacroDriverDecorator driver = MacroFeature.getMacroDriverDecorator();
            driver.setDriver(currentDriver);
            DriverFeature.getDriverManager().setCurrentDriver(driver);
            driver.setPosition(0,0);
        }
    }
}
