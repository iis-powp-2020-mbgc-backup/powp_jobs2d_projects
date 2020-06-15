package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.line.Line2d;

public class Scaling extends Transformation {

	private int scaleFactor;
	
	public Scaling(int scaleFactor) {
		super();
		this.scaleFactor = scaleFactor;
	}
	
	@Override
	protected void properTransformation(List<Line2d> lines) {
		int x = lines.get(0).getStartPosX();
		int y = lines.get(0).getStartPosY();

		// Move drawing to position 0,0
		moveToPosition(lines, -x, -y);

		// scaling
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosX(lines.get(i).getStartPosX() * scaleFactor);
			lines.get(i).setStartPosY(lines.get(i).getStartPosY() * scaleFactor);
			lines.get(i).setEndPosX(lines.get(i).getEndPosX() * scaleFactor);
			lines.get(i).setEndPosY(lines.get(i).getEndPosY() * scaleFactor);
		}

		// move back to original position
		moveToPosition(lines, x, y);
	}
}
