package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.manager.CommandHistoryController;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectHistoryListOptionListener implements ListSelectionListener {

    private DriverCommandManager commandManager;
    private final JList<CommandHistoryController> historyCommandList;

    public SelectHistoryListOptionListener(DriverCommandManager commandManager,
                                           JList<CommandHistoryController> historyCommandList) {
        this.commandManager = commandManager;
        this.historyCommandList = historyCommandList;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (historyCommandList.getSelectedIndex() != -1) {
                int index = historyCommandList.getSelectedIndex();
                commandManager = CommandsFeature.getDriverCommandManager();

                commandManager.setCurrentCommand(CommandHistoryController.getCommandFromList(index));
            }
        }
    }
}
