package edu.kis.powp.jobs2d.command;

public class CommandTransformationRotate extends CommandTransformation {
	private double angle;

	public CommandTransformationRotate(float angleInDegrees) {
		super();
		this.angle = Math.toRadians(angleInDegrees);
	}

	@Override
	int getNewX(int x, int y){
		return (int) (x * Math.cos(angle) - y * Math.sin(angle));
	}

	@Override
	int getNewY(int x, int y){
		return (int) (x * Math.sin(angle) + y * Math.cos(angle));
	}
}
