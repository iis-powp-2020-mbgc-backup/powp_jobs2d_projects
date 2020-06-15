package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.line.Line2d;

public class Move extends Transformation {
	
	private int x;
	
	private int y;
	
	public Move(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	protected void properTransformation(List<Line2d> lines) {
		moveToPosition(lines, x, y);
	}
}
