package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroDriver;
import edu.kis.powp.jobs2d.features.CommandsFeature;

public class SelectExecuteMacro implements ActionListener {

    private DriverManager driverManager;
    private MacroDriver macroDriver;
    private DriverCommandManager driverCommandManager;
    public  SelectExecuteMacro(DriverManager driverManager, MacroDriver macroDriver, DriverCommandManager driverCommandManager) {
        this.driverManager = driverManager;
        this.macroDriver = macroDriver;
        this.driverCommandManager=driverCommandManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        driverCommandManager.setCurrentCommand(macroDriver.getCommands(), "Macro");
        macroDriver.reverseDriver();
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        command.execute(driverManager.getCurrentDriver());
    }
}