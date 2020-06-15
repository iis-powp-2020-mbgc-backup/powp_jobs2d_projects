package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.line.Line2d;

public class FlipVertical extends Transformation {
	
	public FlipVertical() {
		super();
	}

	@Override
	protected void properTransformation(List<Line2d> lines) {
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosY(-lines.get(i).getStartPosY());
			lines.get(i).setEndPosY(-lines.get(i).getEndPosY());
		}
	}
}
