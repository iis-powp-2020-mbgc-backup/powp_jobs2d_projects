package edu.kis.powp.jobs2d.command.manager.parsing;

import edu.kis.powp.jobs2d.command.DriverCommand;
/** Interface for changing text to command and command to text*/
public interface Parser {
	/**Change text to command
	 * @param commandAsString text command
	 * @return command*/
	DriverCommand parseFromString(String commandAsString);
	/**Change command to text
	 * @param driverCommand command to parse
	 * @return command text*/
	String parseToString(DriverCommand driverCommand);
}
