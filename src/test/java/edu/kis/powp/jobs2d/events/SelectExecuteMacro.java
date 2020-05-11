package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

public class SelectExecuteMacro implements ActionListener {

    private DriverManager driverManager;

    public  SelectExecuteMacro(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandsFeature.getDriverCommandManager().setCurrentCommand(MacroFeature.getDriverCommandManager().getCommands(), "Macro");
        MacroFeature.getDriverCommandManager().reverseDriver();
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        command.execute(driverManager.getCurrentDriver());
    }
}