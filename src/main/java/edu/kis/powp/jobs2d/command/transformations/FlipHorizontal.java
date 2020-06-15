package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.line.Line2d;

public class FlipHorizontal extends Transformation {
	
	public FlipHorizontal() {
		super();
	}

	@Override
	protected void properTransformation(List<Line2d> lines) {
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosX(-lines.get(i).getStartPosX());
			lines.get(i).setEndPosX(-lines.get(i).getEndPosX());
		}
	}
}
