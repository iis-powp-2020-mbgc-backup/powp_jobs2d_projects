package edu.kis.powp.jobs2d.command.manager.parsing;

import edu.kis.powp.jobs2d.command.DriverCommand;

public interface Parser {
	DriverCommand parseFromString(String commandAsString);

	String parseToString(DriverCommand driverCommand);
}
