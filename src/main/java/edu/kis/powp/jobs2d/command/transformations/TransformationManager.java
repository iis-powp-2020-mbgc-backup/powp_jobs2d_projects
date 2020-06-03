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
		
		List<DriverCommand> commandList = new ArrayList<>();
		List<Line2d> lines = visitor.getAllCommandsCoordinates();
		
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).setStartPosX(lines.get(i).getStartPosX() + x);
			lines.get(i).setStartPosY(lines.get(i).getStartPosY() + y);
			lines.get(i).setEndPosX(lines.get(i).getEndPosX() + x);
			lines.get(i).setEndPosY(lines.get(i).getEndPosY() + y);
		}
		
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
		
		//List<DriverCommand> driverCommandList = (List<DriverCommand>)
		//		(List<? extends DriverCommand>) commandList;
		return new ImmutableComplexCommand(commandList);
	}
}
