package edu.kis.powp.jobs2d.command.historyComponent;

import edu.kis.powp.jobs2d.command.DriverCommand;

public class CommandHistory {

    private String date;
    private String commandName;
    private DriverCommand command;

    public CommandHistory(String date, String commandName, DriverCommand command) {
        this.date = date;
        this.commandName = commandName;
        this.command = command;
    }

    public DriverCommand getCommand() {
        return command;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Date : " + date + " Name :" + commandName;
    }
}
