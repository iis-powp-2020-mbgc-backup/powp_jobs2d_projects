package edu.kis.powp.jobs2d.command;

public class CommandTransformationScale extends CommandTransformation {
	private double scaleX;
	private double scaleY;

	public CommandTransformationScale(double scaleX, double scaleY) {
		super();
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	@Override
	int getNewX(int x, int y){
		return (int) (x * scaleX);
	}

	@Override
	int getNewY(int x, int y){
		return (int) (y * scaleY);
	}
}
