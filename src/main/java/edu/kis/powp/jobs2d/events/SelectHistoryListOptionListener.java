package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.historyComponent.CommandHistory;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectHistoryListOptionListener implements ListSelectionListener {

    private final DefaultListModel<CommandHistory> historyCommandList;
    private final DriverCommandManager commandManager;

    public SelectHistoryListOptionListener(DriverCommandManager commandManager, DefaultListModel<CommandHistory> historyCommandList) {
        this.commandManager = commandManager;
        this.historyCommandList = historyCommandList;
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            final int selectedIndex = ((JList<CommandHistory>) event.getSource()).getSelectedIndex();
            if (selectedIndex != -1) {
                CommandHistory selectedValue = historyCommandList.get(selectedIndex);
                if (selectedValue != null) {
                    commandManager.setCurrentCommand(selectedValue.getCommand());
                }
            }
        }
    }
}
