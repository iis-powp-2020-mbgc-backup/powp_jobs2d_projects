package edu.kis.powp.jobs2d.CommandDrawerPattern;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandList {

	public static List<DriverCommand> drawingICharacter() {
		List<DriverCommand> driverCommands = new ArrayList<>();
		driverCommands.add(new SetPositionCommand(-20, -50));
		driverCommands.add(new OperateToCommand(-20, -50));
		driverCommands.add(new SetPositionCommand(-20, -40));
		driverCommands.add(new OperateToCommand(-20, 50));
		driverCommands.add(new SetPositionCommand(0, -50));
		return driverCommands;
	}
}
