package edu.kis.powp.jobs2d.command.manager;

import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.CommandVisitorInterface;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.observer.Publisher;

/**
 * Driver command Manager.
 */
public class DriverCommandManager {
	private DriverCommand currentCommand = null;
	private DriverManager driverManager = null;

	private Publisher changePublisher = new Publisher();
	private List<DriverCommand> commandList;
	private String  commandName;

	/**
	 * Set current command.
	 * 
	 * @param commandList Set the command as current.
	 */
	public synchronized void setCurrentCommand(DriverCommand commandList) {
		this.currentCommand = commandList;
		changePublisher.notifyObservers();
	}

	public synchronized void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public synchronized void runCurrentCommand() {
		currentCommand.execute(driverManager.getCurrentDriver());
	}

	/**
	 * Set current command.
	 *
	 * @param commandList list of commands representing a compound command.
	 * @param name        name of the command.
	 */

	public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
		this.commandList = commandList;
		this.commandName = name;

		setCurrentCommand(new CompoundCommand(commandList));
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

	public synchronized List<DriverCommand> getCommandList(){
		return commandList;
	}

	public synchronized String getCommandName(){
		return commandName;
	}
}
