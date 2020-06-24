package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.Visitor;
import java.awt.geom.Point2D;
import java.util.Iterator;


public class CommandLengthVisitor implements Visitor {

	private int currentPosX = 0;
	private int currentPosY = 0;
	private double length = 0;

	public double getLength() {
		return length;
	}

	@Override
	public void visit(OperateToCommand operateToCommand) {
		length += Point2D.distance(this.currentPosX, this.currentPosY, operateToCommand.getPosX(), operateToCommand.getPosY());
		this.currentPosX = operateToCommand.getPosX();
		this.currentPosY = operateToCommand.getPosY();
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		this.currentPosX = setPositionCommand.getPosX();
		this.currentPosY = setPositionCommand.getPosY();
	}

	@Override
	public void visit(ICompoundCommand compoundCommand) {
		Iterator<DriverCommand> iterator = compoundCommand.iterator();
		while (iterator.hasNext())
		{
			DriverCommand driverCommand = iterator.next();
			driverCommand.accept(this);
		}
	}
}
