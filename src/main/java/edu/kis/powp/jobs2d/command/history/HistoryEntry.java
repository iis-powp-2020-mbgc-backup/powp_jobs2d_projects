package edu.kis.powp.jobs2d.command.history;

public class HistoryEntry {

    private String date;
    private String commandName;

    public String getCommandName() {
        return commandName;
    }

    public HistoryEntry(String string, String commandName) {
        this.date = string;
        this.commandName = commandName;
    }

    @Override
    public String toString() {
        return date + "; " + commandName;
    }
}
