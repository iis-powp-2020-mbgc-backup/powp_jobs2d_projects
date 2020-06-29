package edu.kis.powp.jobs2d.command.manager;

import java.util.List;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ImmutableComplexCommand;
import edu.kis.powp.jobs2d.command.gui.CommandManager;
import edu.kis.powp.jobs2d.command.ImmutableCommandCreationException;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

/**
 * Driver command Manager.
 */
public class DriverCommandManager implements CommandManager {
	private DriverCommand currentCommand = null;

	private final Publisher commandChangePublisher = new Publisher();
	private final Publisher observerChangePublisher = new Publisher();

	/**
	 * Set current command.
	 *
	 * @param commandList Set the command as current.
	 */
	public synchronized void setCurrentCommand(DriverCommand commandList) {
		this.currentCommand = commandList;
		commandChangePublisher.notifyObservers();
	}

	/**
	 * Set current command.
	 * 
	 * @param commandList list of commands representing a compound command.
	 * @param name        name of the command.
	 */
	public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
		try {
			setCurrentCommand(new ImmutableComplexCommand(commandList, name));
		} catch (ImmutableCommandCreationException e) {
			System.out.println(e.getMessage());
			setCurrentCommand(new ComplexCommand(commandList));
		}
	}

	/**
	 * Return current command.
	 * 
	 * @return Current command.
	 */
	public synchronized DriverCommand getCurrentCommand() {
		return currentCommand;
	}

	@Override 
	public synchronized void clearCurrentCommand() {
		currentCommand = null;
		commandChangePublisher.notifyObservers();
	}

	@Override
	public synchronized String getCurrentCommandString() {
		if (getCurrentCommand() == null) {
			return "No command loaded";
		} else
			return getCurrentCommand().toString();
	}

	@Override
	public List<Subscriber> getChangeSubscribers() {
		return commandChangePublisher.getSubscribers();
	}

	@Override
	public void addChangeSubscriber(Subscriber subscriber) {
		commandChangePublisher.addSubscriber(subscriber);
		observerChangePublisher.notifyObservers();
	}

	@Override
	public void clearChangeSubscribers() {
		commandChangePublisher.clearObservers();
		observerChangePublisher.notifyObservers();
	}

	@Override
	public void runCommand() {
		if(currentCommand != null) {
		    currentCommand.execute(DriverFeature.getDriverManager().getCurrentDriver());
		}
	}

	public void addObserverChangeSubscriber(Subscriber subscriber) {
		observerChangePublisher.addSubscriber(subscriber);
	}
}
