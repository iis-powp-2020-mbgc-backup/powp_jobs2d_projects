package edu.kis.powp.jobs2d.command.gui;

import java.awt.event.ActionEvent;

public interface WindowInterface {
	void clearCommand();

	void runCommand();

	void updateCurrentCommandField();

	void deleteObservers(ActionEvent e);

	void updateObserverListField();
}
