package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.RotateCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectRotateCommandOptionListener implements ActionListener {
	float angleInDegrees;

	public SelectRotateCommandOptionListener(float angleInDegrees) {
		this.angleInDegrees = angleInDegrees;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DriverCommandManager driverCommandManager = CommandsFeature.getDriverCommandManager();

		RotateCommand rotateCommand = new RotateCommand(angleInDegrees);
		driverCommandManager.getCurrentCommand().accept(rotateCommand);
		ICompoundCommand transformedCommand = rotateCommand.getTransformedCommand();

		driverCommandManager.setCurrentCommand(transformedCommand);
	}
}
