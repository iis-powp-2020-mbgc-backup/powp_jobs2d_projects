package edu.kis.powp.jobs2d.command;

import java.awt.geom.Point2D;
import java.util.Iterator;


public class CommandStatisticVisitor implements Visitor {

	private int operateToCommandCounter = 0;
	private int setPositionCommandCounter = 0;
	private int currentPosX = 0;
	private int currentPosY = 0;
	private double totalLengthCommand = 0;

	public int getOperateToCommandCounter() {
		return operateToCommandCounter;
	}

	public int getSetPositionCommandCounter() {
		return setPositionCommandCounter;
	}

	public int getAllCommandsCounter() {
		return setPositionCommandCounter + operateToCommandCounter;
	}

	public double getTotalLengthCommand() {
		return totalLengthCommand;
	}

	@Override
	public void visit(OperateToCommand operateToCommand) {
		totalLengthCommand += Point2D.distance(this.currentPosX, this.currentPosY, operateToCommand.getPosX(), operateToCommand.getPosY());
		this.currentPosX = operateToCommand.getPosX();
		this.currentPosY = operateToCommand.getPosY();
		operateToCommandCounter++;
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		this.currentPosX = setPositionCommand.getPosX();
		this.currentPosY = setPositionCommand.getPosY();
		setPositionCommandCounter++;
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
