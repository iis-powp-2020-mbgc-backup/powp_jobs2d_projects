package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.transformation.CommandTransformationRotate;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCommandTransformationRotateOptionListener implements ActionListener {

    double angle;

    public SelectCommandTransformationRotateOptionListener (double angle) {
        this.angle = angle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommandManager driverCommandManager = CommandsFeature.getDriverCommandManager();

        CommandTransformationRotate commandTransformationRotate = new CommandTransformationRotate(angle);
        driverCommandManager.getCurrentCommand().accept(commandTransformationRotate);
        ICompoundCommand iCompoundCommand = commandTransformationRotate.getCompoundCommand();

        driverCommandManager.setCurrentCommand(iCompoundCommand);
    }
}
