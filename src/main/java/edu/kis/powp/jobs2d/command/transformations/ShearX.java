package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.line.Line2d;

public class ShearX extends Transformation {

	private int shearValue;
	
	public ShearX(int shearValue) {
		super();
		this.shearValue = shearValue;
	}
	
	@Override
	protected void properTransformation(List<Line2d> lines) {
		
		int x = lines.get(0).getStartPosX();
		int y = lines.get(0).getStartPosY();

		// Move drawing to position 0,0
		moveToPosition(lines, -x, -y);
		int startX, startY, endX, endY;

		for(int i = 0; i < lines.size(); i++) {
			startX = lines.get(i).getStartPosX();
			startY = lines.get(i).getStartPosY();
			endX = lines.get(i).getEndPosX();
			endY = lines.get(i).getEndPosY();

			lines.get(i).setStartPosX( startX + (startY * shearValue) );
			lines.get(i).setEndPosX( endX + (endY * shearValue) );
		}

		//move back to original position
		moveToPosition(lines, x, y);
	}
}
