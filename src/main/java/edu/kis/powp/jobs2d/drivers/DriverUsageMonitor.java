package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

public class DriverUsageMonitor extends Job2dDriverDecorator {
	public DriverUsageMonitor(Job2dDriver driver) {
		super(driver);
	}
	
	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
	}
	
	@Override
	public void operateTo(int x, int y) {
		super.operateTo(x, y);
	}
}
