package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.transformations.CoordsTransformation;

/**
 * Line adapter - Job2dDriver with DrawPanelController object.
 */
public class ScaledLineDriver implements Job2dDriver {
	LineDriverAdapter driver;
	CoordsTransformation transformation;

	public ScaledLineDriver(LineDriverAdapter driver, CoordsTransformation transformation) {
		super();
		this.driver = driver;
		this.transformation = transformation;
	}

	@Override
	public void setPosition(int x, int y) {
			driver.setStartX(transformation.transformXPoint(x, y));
			driver.setStartY(transformation.transformYPoint(x, y));
	}

	@Override
	public void operateTo(int x, int y) {
		if (this.transformation == null) {
			driver.getLine().setStartCoordinates(driver.getStartX(), driver.getStartY());
			this.setPosition(x, y);
			driver.getLine().setEndCoordinates(x, y);
		} else {
			driver.getLine().setStartCoordinates(driver.getStartX(), driver.getStartY());
			this.setPosition(x, y);
			driver.getLine().setEndCoordinates(transformation.transformXPoint(x, y), transformation.transformYPoint(x, y));
		}

		driver.getDrawController().drawLine(driver.getLine());
	}

	@Override
	public String toString() {
		return "2d device simulator - " + driver.getName();
	}
}

