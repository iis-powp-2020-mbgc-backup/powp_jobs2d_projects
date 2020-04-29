package edu.kis.powp.jobs2d.command;


import java.util.Iterator;

/**
 * DriverCommandVisitor class for driver commands.
 */
public class DriverCommandCallCounterVisitor implements DriverCommandVisitor {
	private int counter = 0;

	public int getCounter(){
		return this.counter;
	}

	@Override
	public void visit(ICompoundCommand driverCommand) {
		Iterator<DriverCommand> commandsIterator = driverCommand.iterator();
		while (commandsIterator.hasNext()) {
			DriverCommand partOfCompoundCommand = commandsIterator.next();
			if (partOfCompoundCommand instanceof ICompoundCommand) {
				this.visit((ICompoundCommand)partOfCompoundCommand);
			}
			this.counter++;
		}
	}

	@Override
	public void visit(OperateToCommand driverCommand) {
		counter++;
	}

	@Override
	public void visit(SetPositionCommand driverCommand) {
		counter++;
	}
}
