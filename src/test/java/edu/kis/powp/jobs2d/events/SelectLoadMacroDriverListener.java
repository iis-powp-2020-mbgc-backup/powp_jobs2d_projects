package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.MacroDriverComposite;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadMacroDriverListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MacroDriverComposite macroDriverComposite = MacroFeature.getMacroDriverComposite();

        CommandsFeature.getDriverCommandManager().setCurrentCommand(macroDriverComposite.getDriverCommandList(), "Macro");
        DriverFeature.getDriverManager().setCurrentDriver(macroDriverComposite.getCoreJob2dDriver());
    }
}
