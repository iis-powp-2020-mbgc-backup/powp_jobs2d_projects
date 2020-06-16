package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.history.HistoryEntry;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectHistoryListOptionListener implements ListSelectionListener {

    private final DefaultListModel<HistoryEntry> historyCommandList;
    private final DriverCommandManager commandManager;

    public SelectHistoryListOptionListener(DriverCommandManager commandManager, DefaultListModel<HistoryEntry> historyCommandList) {
        this.commandManager = commandManager;
        this.historyCommandList = historyCommandList;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            final int selectedIndex = ((JList<HistoryEntry>) e.getSource()).getSelectedIndex();
            if (selectedIndex != -1) {
                HistoryEntry selectedValue = historyCommandList.get(selectedIndex);
                if (selectedValue != null) {
                    commandManager.setCurrentCommand(selectedValue.getCommand());
                }
            }
        }
    }

}
