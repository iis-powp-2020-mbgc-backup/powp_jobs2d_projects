package edu.kis.powp.jobs2d.command.gui.backlog;

import edu.kis.powp.jobs2d.command.DriverCommand;

public class BackLogItem {
	private String date;
	private String commandName;
	private DriverCommand command;

	public BackLogItem(String date, String commandName, final DriverCommand command) {
		this.date = date;
		this.commandName = commandName;
		this.command = command;
	}

	public DriverCommand getCommand() {
		return command;
	}

	@Override
	public String toString() {
		return date +
				": " + commandName;
	}
}
