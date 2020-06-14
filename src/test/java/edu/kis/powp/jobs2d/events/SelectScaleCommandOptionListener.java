package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CommandTransformationVisitor;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.transformation.ScalePointTransformation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectScaleCommandOptionListener implements ActionListener {
    private float scaleX;
    private float scaleY;

    public SelectScaleCommandOptionListener(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DriverCommandManager driverCommandManager = CommandsFeature.getDriverCommandManager();

        CommandTransformationVisitor commandTransformationVisitor = new CommandTransformationVisitor(new ScalePointTransformation(scaleX, scaleY));
        driverCommandManager.getCurrentCommand().accept(commandTransformationVisitor);
        ICompoundCommand transformedCommand = commandTransformationVisitor.getTransformedCommand();

        driverCommandManager.setCurrentCommand(transformedCommand);
    }
}
