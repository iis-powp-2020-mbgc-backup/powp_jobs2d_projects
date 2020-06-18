package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.line.Line2d;

public class Rotation extends Transformation {

	private int rotateAngle;
	
	public Rotation(int rotateAngle) {
		super();
		this.rotateAngle = rotateAngle;
	}
	
	@Override
	protected void properTransformation(List<Line2d> lines) {
		int x = lines.get(0).getStartPosX();
		int y = lines.get(0).getStartPosY();

		// Move drawing to position 0,0
		moveToPosition(lines, -x, -y);


		int startX, startY, endX, endY;
		double cosValue = Math.cos(Math.toRadians(rotateAngle));
		double sinValue = Math.sin(Math.toRadians(rotateAngle));

		for(int i = 0; i < lines.size(); i++) {
			startX = lines.get(i).getStartPosX();
			startY = lines.get(i).getStartPosY();
			endX = lines.get(i).getEndPosX();
			endY = lines.get(i).getEndPosY();

			lines.get(i).setStartPosX((int) (startX * cosValue - startY * sinValue));
			lines.get(i).setStartPosY((int) (startY * cosValue + startX * sinValue));
			lines.get(i).setEndPosX((int) (endX * cosValue - endY * sinValue));
			lines.get(i).setEndPosY((int) (endY * cosValue + endX * sinValue));
		}

		// move back to original position
		moveToPosition(lines, x, y);
	}
}
