package edu.kis.powp.jobs2d.command.transformations;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.line.Line2d;

public class Transformation implements ITransformation {
	
	protected Transformation() {
		
	}
	
	@Override
	public ICompoundCommand transform(CommandCoordinatesVisitor visitor) {
		
		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		properTransformation(lines);

		List<DriverCommand> commandList = buildCommandList(lines);

		try {
			return new ImmutableComplexCommand(commandList);
		} catch (ImmutableCommandCreationException e) {
			System.out.println(e.getMessage());
			return new ComplexCommand(commandList);
		}
	}
	
	protected void properTransformation(List<Line2d> lines) {
		
	}

	protected static void moveToPosition(List<Line2d> lines, int x, int y) {
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosX(lines.get(i).getStartPosX() + x);
			lines.get(i).setStartPosY(lines.get(i).getStartPosY() + y);
			lines.get(i).setEndPosX(lines.get(i).getEndPosX() + x);
			lines.get(i).setEndPosY(lines.get(i).getEndPosY() + y);
		}
	}

	private static List<DriverCommand> buildCommandList(List<Line2d> lines) {
		
		List<DriverCommand> commandList = new ArrayList<>();

		for (Line2d line : lines) {
			if (!line.getOperateTo()) {
				commandList.add(new SetPositionCommand(line.getStartPosX(), line.getStartPosY()));
			}
			else {
				commandList.add(new OperateToCommand(line.getEndPosX(), line.getEndPosY()));
			}
		}

		return commandList;
	}

}
