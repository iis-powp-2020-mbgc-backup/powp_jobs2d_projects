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
		for (DriverCommand partOfCompoundCommand : driverCommand) {
			partOfCompoundCommand.accept(this);
		}
	}

	@Override
	public void visit(OperateToCommand driverCommand) {
		this.counter++;
	}

	@Override
	public void visit(SetPositionCommand driverCommand) {
		this.counter++;
	}
}
