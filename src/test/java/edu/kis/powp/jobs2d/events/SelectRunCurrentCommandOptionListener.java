package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectRunCurrentCommandOptionListener implements ActionListener {

    private DriverManager driverManager;

    public SelectRunCurrentCommandOptionListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        command.execute(driverManager.getCurrentDriver());
    }
}
