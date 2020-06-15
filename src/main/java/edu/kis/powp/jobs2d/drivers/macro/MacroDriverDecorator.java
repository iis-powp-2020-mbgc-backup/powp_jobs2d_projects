package edu.kis.powp.jobs2d.drivers.macro;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;
/**Driver decorator that records executed commands*/
public class MacroDriverDecorator implements Job2dDriver {
	/**List of operations*/
	private List<DriverCommand> driverCommandList;
	/**Driver*/
	private Job2dDriver driver;

	public MacroDriverDecorator() {
		this.driverCommandList = new ArrayList<>();
	}
	/**Set list of operations*/
	public MacroDriverDecorator(List<DriverCommand> driverCommandList) {
		this.driverCommandList = driverCommandList;
	}
	/**Set driver*/
	public void setDriver(Job2dDriver driver) {
		this.driver = driver;
	}
	/**Clear list of operations*/
	public void clearCommandSet() {
		driverCommandList = new ArrayList<>();
	}
	/**Get list of operations*/
	public List<DriverCommand> getDriverCommandList() {
		return driverCommandList;
	}
	/**Get driver*/
	public Job2dDriver getDriver() {
		return driver;
	}
	/**Execute setPosition and add it to list of operations
	 * @param x X coordinate
	 * @param y Y coordinate*/
	@Override
	public void setPosition(int x, int y) {
		driverCommandList.add(new SetPositionCommand(x, y));
		driver.setPosition(x,y);
	}
	/**Execute operateTo and add it to list of operations
	 * @param x X coordinate
	 * @param y Y coordinate*/
	@Override
	public void operateTo(int x, int y) {
		driverCommandList.add(new OperateToCommand(x, y));
		driver.operateTo(x,y);
	}
	/**Get name of class
	 * @return name*/
	@Override
	public String toString() {
		return "MacroDriverDecorator";
	}
}
