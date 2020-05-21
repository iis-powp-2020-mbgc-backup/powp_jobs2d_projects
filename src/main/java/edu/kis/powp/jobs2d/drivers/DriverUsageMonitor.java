package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

public class DriverUsageMonitor extends Job2dDriverDecorator {
	Logger logger = Logger.getLogger("global");
	
	private double operateToDistance;
	private double setPositionDistance;
	private int previousPositionX;
	private int previousPositionY;
	
	
	public DriverUsageMonitor(Job2dDriver driver) {
		super(driver);
	}
	
	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		
		this.setPositionDistance += computeDistance(x, y);
		logger.info(driver.toString() + "\nSetPosition distance: " + getSetPositionDistance());
		
		this.previousPositionX = x;
		this.previousPositionY = y;
	}
	
	@Override
	public void operateTo(int x, int y) {
		super.operateTo(x, y);
		
		this.operateToDistance += computeDistance(x, y);
		logger.info(driver.toString() + "\nOperateTo distance: "  + getOperateToDistance());
		
		this.previousPositionX = x;
		this.previousPositionY = y;
	}
	
	public double getOperateToDistance() {
		return this.operateToDistance;
	}
	
	public double getTotalToDistance() {
		return this.operateToDistance + this.setPositionDistance;
	}
	
	public double getSetPositionDistance() {
		return setPositionDistance;
	}
	
	private double computeDistance(int x, int y) {
		return Math.sqrt(Math.pow(x - previousPositionX, 2) + Math.pow(y - previousPositionY, 2));
	}
}
