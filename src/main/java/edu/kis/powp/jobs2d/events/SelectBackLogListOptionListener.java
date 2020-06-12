package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.gui.backlog.BackLogItem;
import edu.kis.powp.jobs2d.command.gui.backlog.BackLogList;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectBackLogListOptionListener implements ListSelectionListener {

	private DriverCommandManager commandManager;

	public SelectBackLogListOptionListener(DriverCommandManager commandManager) {
		this.commandManager = commandManager;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			BackLogItem selectedValue = ((JList<BackLogItem>)e.getSource()).getSelectedValue();
			if (selectedValue != null) {
				commandManager.setCurrentCommand(selectedValue.getCommand());
			}
		}
	}
}
