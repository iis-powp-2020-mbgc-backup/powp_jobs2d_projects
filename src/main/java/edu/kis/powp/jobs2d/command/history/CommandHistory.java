package edu.kis.powp.jobs2d.command.history;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;

public class CommandHistory {

    private static DefaultListModel<HistoryEntry> entryHistoryList = new DefaultListModel<>();

    public static DefaultListModel<HistoryEntry> getEntryHistoryList() {
        return entryHistoryList;
    }

    public static void addCommandEntry(String commandName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        HistoryEntry historyEntry = new HistoryEntry(LocalTime.now().format(formatter), commandName);
        entryHistoryList.addElement(historyEntry);
    }

    public static void clearHistory() {
        entryHistoryList.clear();
    }
}
