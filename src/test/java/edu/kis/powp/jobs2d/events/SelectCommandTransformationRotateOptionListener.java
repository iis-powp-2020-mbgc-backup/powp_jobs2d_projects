package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.CommandTransformationRotate;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

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

		CommandTransformationRotate commandTransformationRotate = new CommandTransformationRotate(angleInDegrees);
		driverCommandManager.getCurrentCommand().accept(commandTransformationRotate);
		ICompoundCommand transformedCommand = commandTransformationRotate.getTransformedCommand();

		driverCommandManager.setCurrentCommand(transformedCommand);
	}
}
