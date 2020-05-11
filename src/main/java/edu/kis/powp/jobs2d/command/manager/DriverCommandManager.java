package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DefaultCompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.DriverCommandVisitor;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Driver command Manager.
 */
public class DriverCommandManager {
	private DriverCommand currentCommand = null;

	private Publisher changePublisher = new Publisher();

	private Collection<Subscriber> cache = null;

	/**
	 * Set current command.
	 *
	 * @param commandList list of commands representing a compound command.
	 * @param name        name of the command.
	 */
	public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
		setCurrentCommand(new DefaultCompoundCommand(commandList));
	}

	/**
	 * Return current command.
	 *
	 * @return Current command.
	 */
	public synchronized DriverCommand getCurrentCommand() {
		return currentCommand;
	}

	/**
	 * Set current command.
	 *
	 * @param commandList Set the command as current.
	 */
	public synchronized void setCurrentCommand(DriverCommand commandList) {
		this.currentCommand = commandList;
		changePublisher.notifyObservers();
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

	/**
	 * Run current command, if set.
	 *
	 */

	public synchronized void runCurrentCommand(){

	}

	/**
	 * Delete observers and move observers collection to cache
	 */

	public synchronized void deleteCurrentObservers(){
		
		if(changePublisher.getSubscribers().size() > 0){
			cache = new ArrayList<>(changePublisher.getSubscribers());
		}

		changePublisher.clearObservers();
	}

	/**
	 * Restores observers from cache
	 * NOTE:
	 * It's probable if addObserver/s feature would be implemented, the cache should be empty
	 */

	public synchronized void resetObservers(){

	}

	/**
	 * Add new observer for list of subscribers that belongs to <code>changePublisher</code>
	 * It should clear cache, if exists.
	 * @param subscriber new observer to add.
	 */
	public synchronized void addObserver(Subscriber subscriber){

	}

	/**
	 * Add observers to existing collection of subscribers.
	 * @param subscribers list to append.
	 */
	public synchronized void addObservers(List<Subscriber> subscribers){

	}

	public Publisher getChangePublisher() {
		return changePublisher;
	}
}
