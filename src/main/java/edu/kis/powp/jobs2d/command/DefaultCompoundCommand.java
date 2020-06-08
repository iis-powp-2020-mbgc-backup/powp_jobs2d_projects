package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.List;

public class DefaultCompoundCommand implements ICompoundCommand {

	private List<DriverCommand> driverCommands = null;
	private String name;

	public DefaultCompoundCommand(List<DriverCommand> collection) {
		this.driverCommands = collection;
		this.name = "";
	}
	public DefaultCompoundCommand(List<DriverCommand> collection, String name) {
		this.driverCommands = collection;
		this.name = name;
	}

	@Override
	public void execute(Job2dDriver driver) {
		driverCommands.forEach((c) -> c.execute(driver));
	}

	@Override
	public Iterator<DriverCommand> iterator() {
		return driverCommands.iterator();
	}

	@Override
	public DriverCommand clone() throws CloneNotSupportedException {
		return ICompoundCommand.super.clone();
	}

	@Override
	public String toString() {
		return name;
	}

}
