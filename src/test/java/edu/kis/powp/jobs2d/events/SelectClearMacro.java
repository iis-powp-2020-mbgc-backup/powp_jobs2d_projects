package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectClearMacro implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();

        manager.clearCurrentCommand();
        manager.clearCommands();

    }
}
