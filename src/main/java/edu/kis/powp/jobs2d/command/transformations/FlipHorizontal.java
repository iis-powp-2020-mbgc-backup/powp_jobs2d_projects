package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.CommandCoordinatesVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.ImmutableComplexCommand;
import edu.kis.powp.jobs2d.command.line.Line2d;

public class FlipHorizontal implements Transformation {

	@Override
	public ICompoundCommand transform(CommandCoordinatesVisitor visitor) {
		List<Line2d> lines = visitor.getAllCommandsCoordinates();
	
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosX(-lines.get(i).getStartPosX());
			lines.get(i).setEndPosX(-lines.get(i).getEndPosX());
		}
	
		List<DriverCommand> commandList = TransformationManager.buildCommandList(lines);
	
		return new ImmutableComplexCommand(commandList);
	}
}
