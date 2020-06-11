package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandHistoryController {

    static List<CommandHistoryEntry> historyEntries = new ArrayList<>();

    public static void addNewHistoryEntry(List<DriverCommand> commands, String name){
        historyEntries.add(new CommandHistoryEntry(commands,name));
    }

    static class CommandHistoryEntry{
        List<DriverCommand> commands;
        String name;
        CommandHistoryEntry(List<DriverCommand> commands, String name){
            this.commands=commands;
            this.name=name;
        }
    }
}
