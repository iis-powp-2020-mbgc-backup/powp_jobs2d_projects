package edu.kis.powp.jobs2d.command.history;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;

import edu.kis.powp.jobs2d.command.DriverCommand;

public class CommandHistory {

    private static DefaultListModel<HistoryEntry> entryHistoryList = new DefaultListModel<>();

    public static DefaultListModel<HistoryEntry> getEntryHistoryList() {
        return entryHistoryList;
    }

    public static void addCommandEntry(String commandName, DriverCommand command) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        HistoryEntry historyEntry = new HistoryEntry(LocalTime.now().format(formatter), commandName, command);
        entryHistoryList.addElement(historyEntry);
    }

    public static void clearHistory() {
        entryHistoryList.clear();
    }
}
