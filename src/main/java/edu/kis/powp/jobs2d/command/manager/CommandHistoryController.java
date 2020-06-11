package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandHistoryController {

    static DefaultListModel listModel;
    static List<CommandHistoryEntry> historyEntries = new ArrayList<>();

    public static void addNewHistoryEntry(List<DriverCommand> commands, String name){
        historyEntries.add(new CommandHistoryEntry(commands,name));
        listModel.add(listModel.getSize(),name);
    }

    static class CommandHistoryEntry{
        List<DriverCommand> commands;
        String name;
        CommandHistoryEntry(List<DriverCommand> commands, String name){
            this.commands=commands;
            this.name=name;
        }
    }

    public static void setListModel(DefaultListModel model) {
        listModel =  model;
    }

    public static List<DriverCommand> getCommandsFromList(int index){
        return historyEntries.get(index).commands;
    }

    public static String getCommandsNameFromList(int index){
        return historyEntries.get(index).name;
    }
}
