package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.macro.MacroDriverDecorator;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectStartMacroListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Job2dDriver job2dDriver = DriverFeature.getDriverManager().getExtensionDriver().getCurrentDriver();
        MacroDriverDecorator driver = MacroFeature.getMacroDriverDecorator();
        driver.clearCommandSet();
        driver.setDriver(job2dDriver);
        driver.getDriver().setPosition(0,0);
        driver.setFlagActive(true);
        DriverFeature.getDriverManager().setCurrentDriver(driver);
    }
}
