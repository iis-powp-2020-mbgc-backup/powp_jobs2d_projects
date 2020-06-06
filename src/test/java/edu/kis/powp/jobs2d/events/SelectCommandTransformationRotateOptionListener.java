package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.transformation.CommandVisitorTransformation;
import edu.kis.powp.jobs2d.command.transformation.CommandVisitorTransformationRotate;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCommandTransformationRotateOptionListener implements ActionListener {

    private double angle;

    public SelectCommandTransformationRotateOptionListener (double angle) {
        this.angle = angle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommandManager driverCommandManager = CommandsFeature.getDriverCommandManager();

        CommandVisitorTransformation commandVisitorTransformation = new CommandVisitorTransformationRotate(angle);
        driverCommandManager.getCurrentCommand().accept(commandVisitorTransformation);
        ICompoundCommand iCompoundCommand = commandVisitorTransformation.getCompoundCommand();

        driverCommandManager.setCurrentCommand(iCompoundCommand);
    }
}
