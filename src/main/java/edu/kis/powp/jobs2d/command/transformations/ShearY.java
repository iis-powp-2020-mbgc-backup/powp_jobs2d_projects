package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.line.Line2d;

public class ShearY extends Transformation {

	private int shearValue;
	
	public ShearY(int shearValue) {
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

			lines.get(i).setStartPosY( startY + (startX * shearValue) );
			lines.get(i).setEndPosY( endY + (endX * shearValue) );
		}

		//move back to original position
		moveToPosition(lines, x, y);
	}
}
