package edu.kis.powp.jobs2d.command.transformations;

import java.util.List;

import edu.kis.powp.jobs2d.command.CommandCoordinatesVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.ImmutableComplexCommand;
import edu.kis.powp.jobs2d.command.line.Line2d;

public class ShearY implements Transformation {

	private int shearValue;
	
	public ShearY(int shearValue) {
		this.shearValue = shearValue;
	}
	
	@Override
	public ICompoundCommand transform(CommandCoordinatesVisitor visitor) {
		
		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		int x = lines.get(0).getStartPosX();
		int y = lines.get(0).getStartPosY();

		// Move drawing to position 0,0
		TransformationManager.moveToPosition(lines, -x, -y);
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
		TransformationManager.moveToPosition(lines, x, y);

		List<DriverCommand> commandList = TransformationManager.buildCommandList(lines);

		return new ImmutableComplexCommand(commandList);
	}
}
