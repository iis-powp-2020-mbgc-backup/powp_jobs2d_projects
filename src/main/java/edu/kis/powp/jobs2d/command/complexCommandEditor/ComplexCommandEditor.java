package edu.kis.powp.jobs2d.command.complexCommandEditor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.builders.ComplexCommandBuilder;

public class ComplexCommandEditor extends AbstractComplexCommandEditor{

	ComplexCommandEditor(ICompoundCommand complexCommand){
		this.complexCommand = complexCommand;
		this.complexCommandBuilder = new ComplexCommandBuilder();
		this.complexCommand.iterator().forEachRemaining(command -> this.complexCommandBuilder.addCommand(command));

	}

	@Override
	public ComplexCommand getEditedComplexCommand() {
		return complexCommandBuilder.build();
	}

	@Override
	public void addCommand(DriverCommand command) {
		complexCommandBuilder.addCommand(command);
	}

	@Override
	public void deleteCommand(int commandIndex) {
		complexCommandBuilder.deleteCommand(commandIndex);
	}

	@Override
	public void moveCommandUp(int commandIndex) {
		complexCommandBuilder.interchangeCommands(commandIndex, commandIndex - 1);
	}

	@Override
	public void moveCommandDown(int commandIndex) {
		complexCommandBuilder.interchangeCommands(commandIndex, commandIndex + 1);
	}

	@Override
	public void modifyCoordinates(int commandIndex, int x, int y) {
		complexCommandBuilder.modifyCoordinates(commandIndex, x, y);
	}
}
