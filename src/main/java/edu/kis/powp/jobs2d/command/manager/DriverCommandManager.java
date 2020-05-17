package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.gui.WindowInterface;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

/**
 * Driver command Manager.
 */
public class DriverCommandManager implements WindowInterface {
	private DriverCommand currentCommand = null;

	private Publisher changePublisher = new Publisher();

	/**
	 * Set current command.
	 *
	 * @param commandList Set the command as current.
	 */
	public synchronized void setCurrentCommand(DriverCommand commandList) {
		this.currentCommand = commandList;
		changePublisher.notifyObservers();
	}

	/**
	 * Set current command.
	 * 
	 * @param commandList list of commands representing a compound command.
	 * @param name        name of the command.
	 */
	public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
		setCurrentCommand(new ICompoundCommand() {

			List<DriverCommand> driverCommands = commandList;

			@Override
			public void execute(Job2dDriver driver) {
				driverCommands.forEach((c) -> c.execute(driver));
			}

			@Override
			public Iterator<DriverCommand> iterator() {
				return driverCommands.iterator();
			}

			@Override
			public String toString() {
				return name;
			}
		});

	}

	/**
	 * Return current command.
	 * 
	 * @return Current command.
	 */
	public synchronized DriverCommand getCurrentCommand() {
		return currentCommand;
	}

	public synchronized void clearCurrentCommand() {
		currentCommand = null;
	}

	public synchronized String getCurrentCommandString() {
		if (getCurrentCommand() == null) {
			return "No command loaded";
		} else
			return getCurrentCommand().toString();
	}

	public Publisher getChangePublisher() {
		return changePublisher;
	}

	@Override
	public void clearCommand() {
		clearCurrentCommand();
		updateCurrentCommandField();
	}

	@Override
	public void runCommand() {
		getCurrentCommand().execute(DriverFeature.getDriverManager().getCurrentDriver());
	}

	@Override
	public void updateCurrentCommandField() {
		currentCommandField.setText(getCurrentCommandString());
	}

	@Override
	public void deleteObservers(ActionEvent e) {
		JToggleButton button = (JToggleButton) e.getSource();
		if (button.isSelected()) {
			getChangePublisher().clearObservers();
			updateObserverListField();
			button.setText("Reset observers");
		} else {
			updateObserverListField();
			button.setText("Delete observers");
		}
	}

	@Override
	public void updateObserverListField() {
		String observerListString = "";
		List<Subscriber> commandChangeSubscribers = getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
	}
}
