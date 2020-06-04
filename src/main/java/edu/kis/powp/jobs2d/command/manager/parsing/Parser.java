package edu.kis.powp.jobs2d.command.manager.parsing;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.io.File;
import java.util.List;

public interface Parser {
	List<DriverCommand> parseFromImport();

	String getCommandName();

	File parseToExport(DriverCommand driverCommand);
}
