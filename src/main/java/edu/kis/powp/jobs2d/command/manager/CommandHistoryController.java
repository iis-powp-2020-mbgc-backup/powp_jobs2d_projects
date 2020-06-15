package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandHistoryController {

    private static DefaultListModel listModel;
    private static List<CommandHistoryEntry> historyEntries = new ArrayList<>();

    public static void addNewHistoryEntry(DriverCommand command, String name) {
        historyEntries.add(new CommandHistoryEntry(command, name));
        listModel.add(listModel.getSize(), name);
    }

    static class CommandHistoryEntry {
        DriverCommand command;
        String name;

        CommandHistoryEntry(DriverCommand command, String name) {
            this.command = command;
            this.name = name;
        }
    }

    public static void setListModel(DefaultListModel model) {
        listModel = model;
    }

    public static DriverCommand getCommandFromList(int index) {
        return historyEntries.get(index).command;
    }
}
