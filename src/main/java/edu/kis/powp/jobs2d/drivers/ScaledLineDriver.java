package edu.kis.powp.jobs2d.drivers;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.transformations.DriverLevelTransformation;

/**
 * Line adapter - Job2dDriver with DrawPanelController object.
 */
public class ScaledLineDriver implements Job2dDriver {
	private ILine line;
	private int startX = 0, startY = 0;
	private String name;
	private DriverLevelTransformation transformation;

	private DrawPanelController drawController;

	public ScaledLineDriver(DrawPanelController drawController, ILine line, String name, DriverLevelTransformation transformation) {
		super();
		this.drawController = drawController;
		this.line = line;
		this.name = name;
		this.transformation = transformation;
	}

	@Override
	public void setPosition(int x, int y) {
		if (transformation == null)
		{
			this.startX = x;
			this.startY = y;
		}
		else
		{
			this.startX = transformation.transformXPoint(x, y);
			this.startY = transformation.transformYPoint(x, y);
		}
	}

	@Override
	public void operateTo(int x, int y) {
		if (this.transformation == null) {
			line.setStartCoordinates(this.startX, this.startY);
			this.setPosition(x, y);
			line.setEndCoordinates(x, y);
		} else {
			line.setStartCoordinates(this.startX, this.startY);
			this.setPosition(x, y);
			line.setEndCoordinates(transformation.transformXPoint(x, y), transformation.transformYPoint(x, y));
		}

		drawController.drawLine(line);
	}

	@Override
	public String toString() {
		return "2d device simulator - " + name;
	}
}

