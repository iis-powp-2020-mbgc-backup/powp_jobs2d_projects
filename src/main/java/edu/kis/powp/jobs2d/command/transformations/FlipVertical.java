package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.CommandCoordinatesVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.ImmutableComplexCommand;
import edu.kis.powp.jobs2d.command.line.Line2d;

public class FlipVertical implements Transformation {

	@Override
	public ICompoundCommand transform(CommandCoordinatesVisitor visitor) {
		
		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosY(-lines.get(i).getStartPosY());
			lines.get(i).setEndPosY(-lines.get(i).getEndPosY());
		}

		List<DriverCommand> commandList = TransformationManager.buildCommandList(lines);

		return new ImmutableComplexCommand(commandList);
	}
}
