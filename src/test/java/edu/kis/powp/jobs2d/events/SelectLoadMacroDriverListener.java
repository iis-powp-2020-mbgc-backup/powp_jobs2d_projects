package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroDriver;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadMacroDriverListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MacroDriver macroDriver = MacroFeature.getMacroDriver();
        DriverManager driverManager = DriverFeature.getDriverManager();

        CommandsFeature.getDriverCommandManager().setCurrentCommand(macroDriver.getDriverCommandList(), "Macro");

        if(driverManager.getCurrentDriver() instanceof MacroDriver){
            driverManager.setCurrentDriver(macroDriver.getDriverComposite().getJob2dDriverList().get(0));
        }
    }
}
