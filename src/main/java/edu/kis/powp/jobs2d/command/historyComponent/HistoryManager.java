package edu.kis.powp.jobs2d.command.historyComponent;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import java.time.format.DateTimeFormatter;

public class HistoryManager {

    private static final String HISTORY_TIME_PATTERN = "HH:mm:ss";
    public static DefaultListModel<CommandHistory> historyEntryDefaultListModel;
    public static DriverCommandManager commandManager;

    public HistoryManager(DriverCommandManager commandManager) {
        historyEntryDefaultListModel = new DefaultListModel<>();
        HistoryManager.commandManager = commandManager;
    }

    public void updateCommandListField() {
        CommandHistory history = new CommandHistory(java.time.LocalTime.now().format(DateTimeFormatter.ofPattern(HISTORY_TIME_PATTERN)),
                commandManager.getCurrentCommandString(), commandManager.getCurrentCommand());
        historyEntryDefaultListModel.addElement(history);
    }

    public void clearHistoryOfCommand() {
        if (historyEntryDefaultListModel != null)
            historyEntryDefaultListModel.clear();
    }

}
