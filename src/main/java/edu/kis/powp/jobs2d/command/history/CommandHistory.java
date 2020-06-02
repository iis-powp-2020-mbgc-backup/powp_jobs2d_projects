package edu.kis.powp.jobs2d.command.history;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;

public class CommandHistory {

    static DefaultListModel<HistoryEntry> entryHistoryList = new DefaultListModel<>();

    public static void addCommandEntry(String commandName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        HistoryEntry historyEntry = new HistoryEntry(LocalTime.now().format(formatter), commandName);
        entryHistoryList.addElement(historyEntry);
    }

    public static void clearHistory() {
        entryHistoryList.clear();
    }
}
