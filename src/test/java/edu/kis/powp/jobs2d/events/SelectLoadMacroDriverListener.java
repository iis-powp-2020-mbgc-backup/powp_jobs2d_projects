package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.MacroDriver;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadMacroDriverListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MacroDriver macroDriver = MacroFeature.getMacroDriver();
        CommandsFeature.getDriverCommandManager().setCurrentCommand(macroDriver.getDriverCommandList(), "Macro");
    }
}
