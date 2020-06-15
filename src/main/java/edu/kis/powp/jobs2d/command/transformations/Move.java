package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.CommandCoordinatesVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.ImmutableComplexCommand;
import edu.kis.powp.jobs2d.command.line.Line2d;

public class Move implements Transformation {
	
	private int x;
	
	private int y;
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public ICompoundCommand transform(CommandCoordinatesVisitor visitor) {
		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		TransformationManager.moveToPosition(lines, x, y);

		List<DriverCommand> commandList = TransformationManager.buildCommandList(lines);

		return new ImmutableComplexCommand(commandList);
	}
}
