package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.transformations.CoordsTransformation;


public class Driver2dTransformationsDecorator implements Job2dDriver
{
	private Job2dDriver driver;
	private CoordsTransformation transformation;

	public Driver2dTransformationsDecorator(Job2dDriver driver, CoordsTransformation transformation)
	{
		this.driver = driver;
		this.transformation = transformation;
	}

	@Override
	public void setPosition(int x, int y)
	{
		driver.setPosition(transformation.transformXPoint(x, y), transformation.transformYPoint(x, y));
	}

	@Override
	public void operateTo(int x, int y)
	{
		driver.operateTo(transformation.transformXPoint(x, y), transformation.transformYPoint(x, y));
	}

	@Override
	public String toString()
	{
		return driver.toString() + " " + transformation.toString();
	}
}

