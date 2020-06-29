package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.Visitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.command.line.Line2d;


public class CommandCoordinatesVisitor implements Visitor {
	
	private List<Line2d> lines = new ArrayList<>();

	public List<Line2d> getAllCommandsCoordinates() {
		return lines;
	}

	@Override
	public void visit(OperateToCommand operateToCommand) {
		Line2d line = lines.get(lines.size() - 1);
		lines.add(new Line2d(line.getEndPosX(), line.getEndPosY(),
				operateToCommand.getPosX(), operateToCommand.getPosY()));

	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		lines.add(new Line2d(setPositionCommand.getPosX(), setPositionCommand.getPosY()));
	}

	@Override
	public void visit(ICompoundCommand compoundCommand) {
		Iterator<DriverCommand> iterator = compoundCommand.iterator();
		while (iterator.hasNext()) {
			DriverCommand driverCommand = iterator.next();
			driverCommand.accept(this);
		}
	}
}
