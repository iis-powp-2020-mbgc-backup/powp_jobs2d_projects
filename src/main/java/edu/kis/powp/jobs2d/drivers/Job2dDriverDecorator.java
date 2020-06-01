package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.List;

public abstract class Job2dDriverDecorator implements Job2dDriver {
	protected Job2dDriver driver;
	
	public Job2dDriverDecorator(Job2dDriver driver) {
		this.driver = driver;
	}
	
	@Override
	public void setPosition(int x, int y) {
		driver.setPosition(x, y);
	}
	
	@Override
	public void operateTo(int x, int y) {
		driver.operateTo(x, y);
	}
}
