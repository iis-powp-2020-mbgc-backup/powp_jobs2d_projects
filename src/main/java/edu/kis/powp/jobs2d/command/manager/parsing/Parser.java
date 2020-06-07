package edu.kis.powp.jobs2d.command.manager.parsing;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.io.File;

public interface Parser {
	DriverCommand parseFromImport(String commandAsString);

	void parseToExport(DriverCommand driverCommand, File fileToExportTo);
}
