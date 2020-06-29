package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;

public class DriverCompositeFeature {

    private static DriverComposite driverComposite;

    public static void setupDriverComposite() {
        driverComposite = new DriverComposite();
        driverComposite.addDriver(DriverFeature.getDriverManager().getCurrentDriver());
        driverComposite.addDriver(MacroFeature.getMacroDriver());
    }

    public static DriverComposite getDriverComposite() {
        return driverComposite;
    }

    public static void setDrawingDriver(Job2dDriver job2dDriver) {
        driverComposite.getJob2dDriverList().set(0, job2dDriver);
    }

    public static Job2dDriver getDrawingDriver() {
        return driverComposite.getJob2dDriverList().get(0);
    }
}
