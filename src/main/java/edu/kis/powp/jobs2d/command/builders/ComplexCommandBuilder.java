package edu.kis.powp.jobs2d.command.builders;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.exceptions.InvalidCommandIndex;

import java.util.ArrayList;
import java.util.List;

public class ComplexCommandBuilder implements CommandBuilder {

	private final List<DriverCommand> commands;

	public ComplexCommandBuilder() {
		commands = new ArrayList<>();
	}

	public void addCommand(DriverCommand command) {
		commands.add(command);
	}

	public void deleteCommand(int commandIndex) throws InvalidCommandIndex {
		if (commands.isEmpty() || commandIndex >= commands.size() || commandIndex < 0) {
			throw new InvalidCommandIndex();
		}

		commands.remove(commandIndex);
	}

	public void interchangeCommands(int commandIndex1, int commandIndex2) throws InvalidCommandIndex {
		if (commands.isEmpty() || commandIndex1 >= commands.size() || commandIndex1 < 0 || commandIndex2 >= commands.size() || commandIndex2 < 0) {
			throw new InvalidCommandIndex();
		}

		DriverCommand tmp = commands.get(commandIndex1);
		commands.set(commandIndex1, commands.get(commandIndex2));
		commands.set(commandIndex2, tmp);
	}

	public void modifyCoordinates(int commandIndex, int x, int y) throws InvalidCommandIndex {
		if (commands.isEmpty() || commandIndex >= commands.size() || commandIndex < 0) {
			throw new InvalidCommandIndex();
		}

		DriverCommand command = commands.get(commandIndex);
		if (command instanceof OperateToCommand) {
			commands.set(commandIndex, new OperateToCommand(x, y));
		} else if (command instanceof SetPositionCommand) {
			commands.set(commandIndex, new SetPositionCommand(x, y));
		}
	}

	@Override
	public ComplexCommand build() {
		return new ComplexCommand(commands);
	}
}
