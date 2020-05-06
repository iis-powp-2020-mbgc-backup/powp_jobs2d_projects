package edu.kis.powp.jobs2d.command.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.observer.Publisher;

/**
 * Driver command Manager.
 */
public class DriverCommandManager implements Job2dDriver {
	private DriverCommand currentCommand = null;
	private boolean macroState=false;
	private List<String> names=new ArrayList<>();
	private Publisher changePublisher = new Publisher();
	List<DriverCommand> commands = new ArrayList<DriverCommand>();
	List<List<DriverCommand>> macro= new ArrayList<>();
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
		if(macroState) {
			macro.add(commandList);
			names.add(name);
		}
		setCurrentCommand(new ICompoundCommand() {

			List<DriverCommand> driverCommands = commandList;


			@Override
			public void execute(Job2dDriver driver) {
				for (List<DriverCommand>  driverCommands: macro
					 ) {
					driverCommands.forEach((c) -> c.execute(driver));
				}

			}

			@Override
			public Iterator<DriverCommand> iterator() {
				return driverCommands.iterator();
			}

			@Override
			public String toString() {
				return names.toString();
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
		names.clear();
	}
	public boolean getMacroState(){
		return macroState;
	}
	public void changeMacroState(){
		macroState=!macroState;
	}
	public synchronized String getCurrentCommandString() {
		if (names.size()==0) {
			return "No command loaded";
		} else
			return names.toString();
	}

	public Publisher getChangePublisher() {
		return changePublisher;
	}

	@Override
	public void setPosition(int i, int i1) {
		commands.add(new SetPositionCommand(i, i1));
	}

	@Override
	public void operateTo(int i, int i1) {
		commands.add(new OperateToCommand(i, i1));
	}
	public List<DriverCommand> getCommands(){
		return commands;

	}
	public void clearCommands(){
		commands.clear();
		names.clear();
	}

}
