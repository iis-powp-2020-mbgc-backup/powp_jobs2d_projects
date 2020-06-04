package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.jobs2d.command.DriverCommand;

public class HistoryEntry {

    private String date;
    private String commandName;
    private DriverCommand command;

    public String getCommandName() {
        return commandName;
    }

    public HistoryEntry(String date, String commandName, DriverCommand command) {
        this.date = date;
        this.commandName = commandName;
        this.command = command;
    }

    public DriverCommand getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return date + "; " + commandName;
    }
}
