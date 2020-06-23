package edu.kis.powp.jobs2d.command.complexCommandEditor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.builders.ComplexCommandBuilder;
import edu.kis.powp.jobs2d.command.exceptions.InvalidCommandIndex;

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
		try {
			complexCommandBuilder.deleteCommand(commandIndex);
		} catch (InvalidCommandIndex invalidCommandIndex) {
			invalidCommandIndex.printStackTrace();
		}
	}

	@Override
	public void moveCommandUp(int commandIndex) {
		try {
			complexCommandBuilder.interchangeCommands(commandIndex, commandIndex - 1);
		} catch (InvalidCommandIndex invalidCommandIndex) {
			//TODO should return error in the application logs, not in the console
			invalidCommandIndex.printStackTrace();
		}
	}

	@Override
	public void moveCommandDown(int commandIndex) {
		try {
			complexCommandBuilder.interchangeCommands(commandIndex, commandIndex + 1);
		} catch (InvalidCommandIndex invalidCommandIndex) {
			//TODO should return error in the application logs, not in the console
			invalidCommandIndex.printStackTrace();
		}
	}

	@Override
	public void modifyCoordinates(int commandIndex, int x, int y) {
		try {
			complexCommandBuilder.modifyCoordinates(commandIndex, x, y);
		} catch (InvalidCommandIndex invalidCommandIndex) {
			//TODO should return error in the application logs, not in the console
			invalidCommandIndex.printStackTrace();
		}
	}
}
