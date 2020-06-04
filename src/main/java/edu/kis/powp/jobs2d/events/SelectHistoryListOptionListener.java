package edu.kis.powp.jobs2d.events;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.kis.powp.jobs2d.command.history.HistoryEntry;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

public class SelectHistoryListOptionListener implements ListSelectionListener {

    private DriverCommandManager commandManager;
    private final JList<HistoryEntry> historyCommandList;

    public SelectHistoryListOptionListener(DriverCommandManager commandManager,
            JList<HistoryEntry> historyCommandList) {
        this.commandManager = commandManager;
        this.historyCommandList = historyCommandList;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            HistoryEntry selectedValue = historyCommandList.getSelectedValue();
            if (selectedValue != null) {
                commandManager.setCurrentCommand(selectedValue.getCommand());
            }
        }
    }

}
