package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.macro.MacroDriverDecorator;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadMacroListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MacroDriverDecorator driver = MacroFeature.getMacroDriverDecorator();
        CommandsFeature.getDriverCommandManager().setCurrentCommand(driver.getDriverCommandList(), "Macro");
        DriverFeature.getDriverManager().setCurrentDriver(driver.getDriver());
    }
}
