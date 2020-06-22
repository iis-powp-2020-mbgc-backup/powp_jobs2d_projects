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

		if(lines.size() >= 1) {
			commandList.add(new SetPositionCommand(lines.get(0).getStartPosX(),
					lines.get(0).getStartPosY()));
		}

		for(int i = 1; i < lines.size(); i++) {
			if(Line2d.checkIfConnected(lines.get(i - 1), lines.get(i))) {
				commandList.add(new OperateToCommand(lines.get(i).getStartPosX(),
						lines.get(i).getStartPosY()));
			}
			else {
				commandList.add(new SetPositionCommand(lines.get(i).getStartPosX(),
						lines.get(i).getStartPosY()));
			}
		}

		if(lines.size() >= 1) {
			Line2d l = lines.get(lines.size()-1);
			commandList.add(new OperateToCommand(l.getEndPosX(),
					l.getEndPosY()));
		}

		return commandList;
	}

}
