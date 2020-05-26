package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

public class DriverUsageMonitor extends Job2dDriverDecorator {
	Logger logger = Logger.getLogger("usagemonitor");
	
	private int operateToDistance = 0;
	private int setPositionDistance = 0;
	private int previousPositionX = 0;
	private int previousPositionY = 0;
	
	
	public DriverUsageMonitor(Job2dDriver driver) {
		super(driver);
	}
	
	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		
		this.setPositionDistance += computeDistance(x, y);
		logger.info("SetPosition distance: " + getSetPositionDistance()
				+ "\nTotal distance: " + getTotalToDistance());
		
		this.previousPositionX = x;
		this.previousPositionY = y;
	}
	
	@Override
	public void operateTo(int x, int y) {
		super.operateTo(x, y);
		
		this.operateToDistance += computeDistance(x, y);
		logger.info("OperateTo distance: " + getOperateToDistance()
				+ "\nTotal distance: " + getTotalToDistance());
		
		this.previousPositionX = x;
		this.previousPositionY = y;
	}
	
	public int getOperateToDistance() {
		return this.operateToDistance;
	}
	
	public int getTotalToDistance() {
		return this.operateToDistance + this.setPositionDistance;
	}
	
	public int getSetPositionDistance() {
		return setPositionDistance;
	}
	
	@Override
	public String toString() {
		return driver.toString();
	}
	
	private double computeDistance(int x, int y) {
		return Math.sqrt(Math.pow(x - previousPositionX, 2) + Math.pow(y - previousPositionY, 2));
	}
}
