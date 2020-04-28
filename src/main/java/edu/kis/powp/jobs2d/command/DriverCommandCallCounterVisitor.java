package edu.kis.powp.jobs2d.command;

import java.util.Iterator;


/**
 * Visitor class for driver commands.
 */
public class DriverCommandCallCounterVisitor implements Visitor {
	private int counter = 0;

	public int getCounter(){
		return this.counter;
	}

	@Override
	public void visit(DriverCommand driverCommand) {
	    counter++;
	}
}
