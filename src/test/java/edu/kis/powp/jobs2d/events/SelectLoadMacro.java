package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroDriverDecorator;


public class SelectLoadMacro implements ActionListener {

    private DriverManager driverManager;
    private MacroDriverDecorator macroDriverDecorator;
    private DriverCommandManager driverCommandManager;

    public SelectLoadMacro(DriverManager driverManager, MacroDriverDecorator macroDriverDecorator, DriverCommandManager driverCommandManager) {
        this.driverManager = driverManager;
        this.macroDriverDecorator = macroDriverDecorator;
        this.driverCommandManager=driverCommandManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        driverCommandManager.setCurrentCommand(macroDriverDecorator.getCommands(), "Macro");
        driverManager.setCurrentDriver(macroDriverDecorator.getPreviousDriver());
    }
}