package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CommandTransformationVisitor;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.transformation.RotatePointTransformation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCommandTransformationRotateOptionListener implements ActionListener {
    float angleInDegrees;

    public SelectCommandTransformationRotateOptionListener(float angleInDegrees) {
        this.angleInDegrees = angleInDegrees;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DriverCommandManager driverCommandManager = CommandsFeature.getDriverCommandManager();

        CommandTransformationVisitor commandTransformationVisitor = new CommandTransformationVisitor(new RotatePointTransformation(angleInDegrees));
        driverCommandManager.getCurrentCommand().accept(commandTransformationVisitor);
        ICompoundCommand transformedCommand = commandTransformationVisitor.getTransformedCommand();

        driverCommandManager.setCurrentCommand(transformedCommand);
    }
}
