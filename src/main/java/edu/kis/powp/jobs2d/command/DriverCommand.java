package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

/**
 * DriverCommand interface.
 */
public interface DriverCommand extends Cloneable {

	/**
	 * Execute command on driver.
	 *
	 * @param driver driver.
	 */
	public void execute(Job2dDriver driver);
	
	/**
	 * Accept object using visitor.
	 *
	 * @param visitor visitor
	 */
	void accept(DriverCommandVisitor visitor);
	
	public DriverCommand clone() throws CloneNotSupportedException;
}
