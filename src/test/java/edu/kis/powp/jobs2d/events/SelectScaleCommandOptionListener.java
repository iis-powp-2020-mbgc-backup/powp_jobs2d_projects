package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.CommandTransformationScale;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

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

		CommandTransformationScale commandTransformationScale = new CommandTransformationScale(scaleX, scaleY);
		driverCommandManager.getCurrentCommand().accept(commandTransformationScale);
		ICompoundCommand transformedCommand = commandTransformationScale.getTransformedCommand();

		driverCommandManager.setCurrentCommand(transformedCommand);
	}
}
