package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.CommandCoordinatesVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.ImmutableComplexCommand;
import edu.kis.powp.jobs2d.command.line.Line2d;

public class Scaling implements Transformation {

	private int scaleFactor;
	
	public Scaling(int scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
	@Override
	public ICompoundCommand transform(CommandCoordinatesVisitor visitor) {
		
		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		int x = lines.get(0).getStartPosX();
		int y = lines.get(0).getStartPosY();

		// Move drawing to position 0,0
		TransformationManager.moveToPosition(lines, -x, -y);

		// scaling
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosX(lines.get(i).getStartPosX() * scaleFactor);
			lines.get(i).setStartPosY(lines.get(i).getStartPosY() * scaleFactor);
			lines.get(i).setEndPosX(lines.get(i).getEndPosX() * scaleFactor);
			lines.get(i).setEndPosY(lines.get(i).getEndPosY() * scaleFactor);
		}

		// move back to original position
		TransformationManager.moveToPosition(lines, x, y);

		List<DriverCommand> commandList = TransformationManager.buildCommandList(lines);

		return new ImmutableComplexCommand(commandList);
	}
}
