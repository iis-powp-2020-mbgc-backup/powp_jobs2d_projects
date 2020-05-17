package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.observer.Publisher;


public interface CommandManager {
	void clearCurrentCommand();

	void runCommand();

	String getCurrentCommandString();

	Publisher getChangePublisher();
}
