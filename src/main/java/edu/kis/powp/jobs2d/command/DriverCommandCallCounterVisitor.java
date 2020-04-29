package edu.kis.powp.jobs2d.command;


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
		counter++;
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
