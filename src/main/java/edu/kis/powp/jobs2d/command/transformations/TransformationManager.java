package edu.kis.powp.jobs2d.command.transformations;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.command.CommandCoordinatesVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.ImmutableComplexCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.line.Line2d;

public class TransformationManager {
	
	public ICompoundCommand moveCommand(CommandCoordinatesVisitor visitor, int x, int y) {

		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		moveToPosition(lines, x, y);

		List<DriverCommand> commandList = buildCommandList(lines);

		return new ImmutableComplexCommand(commandList);
	}

	public ICompoundCommand flipHorizontalCommand(CommandCoordinatesVisitor visitor) {

		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosX(-lines.get(i).getStartPosX());
			lines.get(i).setEndPosX(-lines.get(i).getEndPosX());
		}

		List<DriverCommand> commandList = buildCommandList(lines);

		return new ImmutableComplexCommand(commandList);
	}

	public ICompoundCommand flipVerticalCommand(CommandCoordinatesVisitor visitor) {

		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosY(-lines.get(i).getStartPosY());
			lines.get(i).setEndPosY(-lines.get(i).getEndPosY());
		}

		List<DriverCommand> commandList = buildCommandList(lines);

		return new ImmutableComplexCommand(commandList);
	}

	public ICompoundCommand scaleCommand(CommandCoordinatesVisitor visitor, int scaleFactor) {

		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		int x = lines.get(0).getStartPosX();
		int y = lines.get(0).getStartPosY();

		// Move drawing to position 0,0
		moveToPosition(lines, -x, -y);

		// scaling
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosX(lines.get(i).getStartPosX() * scaleFactor);
			lines.get(i).setStartPosY(lines.get(i).getStartPosY() * scaleFactor);
			lines.get(i).setEndPosX(lines.get(i).getEndPosX() * scaleFactor);
			lines.get(i).setEndPosY(lines.get(i).getEndPosY() * scaleFactor);
		}

		// move back to original position
		moveToPosition(lines, x, y);

		List<DriverCommand> commandList = buildCommandList(lines);

		return new ImmutableComplexCommand(commandList);
	}

	public ICompoundCommand rotateCommand(CommandCoordinatesVisitor visitor, int rotateAngle) {

		List<Line2d> lines = visitor.getAllCommandsCoordinates();

		int x = lines.get(0).getStartPosX();
		int y = lines.get(0).getStartPosY();

		// Move drawing to position 0,0
		moveToPosition(lines, -x, -y);


		int startX, startY, endX, endY;
		double cosValue = Math.cos(Math.toRadians(rotateAngle));
		double sinValue = Math.sin(Math.toRadians(rotateAngle));

		for(int i = 0; i < lines.size(); i++) {
			startX = lines.get(i).getStartPosX();
			startY = lines.get(i).getStartPosY();
			endX = lines.get(i).getEndPosX();
			endY = lines.get(i).getEndPosY();

			lines.get(i).setStartPosX((int) (startX * cosValue - startY * sinValue));
			lines.get(i).setStartPosY((int) (startY * cosValue + startX * sinValue));
			lines.get(i).setEndPosX((int) (endX * cosValue - endY * sinValue));
			lines.get(i).setEndPosY((int) (endY * cosValue + endX * sinValue));
		}

		// move back to original position
		moveToPosition(lines, x, y);

		List<DriverCommand> commandList = buildCommandList(lines);

		return new ImmutableComplexCommand(commandList);
	}

	private void moveToPosition(List<Line2d> lines, int x, int y) {
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosX(lines.get(i).getStartPosX() + x);
			lines.get(i).setStartPosY(lines.get(i).getStartPosY() + y);
			lines.get(i).setEndPosX(lines.get(i).getEndPosX() + x);
			lines.get(i).setEndPosY(lines.get(i).getEndPosY() + y);
		}
	}

	private List<DriverCommand> buildCommandList(List<Line2d> lines) {
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
