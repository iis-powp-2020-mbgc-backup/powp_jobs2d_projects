package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorTransformation;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorTransformationFlip;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCommandTransformationFlipOptionListener implements ActionListener {
    private boolean isHorizontal;

    public SelectCommandTransformationFlipOptionListener (boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommandManager driverCommandManager = CommandsFeature.getDriverCommandManager();

        CommandVisitorTransformation commandVisitorTransformation = new CommandVisitorTransformationFlip(this.isHorizontal);
        driverCommandManager.getCurrentCommand().accept(commandVisitorTransformation);
        ICompoundCommand iCompoundCommand = commandVisitorTransformation.getCompoundCommand();

        driverCommandManager.setCurrentCommand(iCompoundCommand);
    }
}