package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandTransformationRotate implements CommandVisitorInterface {
	private List<DriverCommand> commands;
	private double angle;

	public CommandTransformationRotate(float angleInDegrees) {
		this.angle = Math.toRadians(angleInDegrees);
		commands = new ArrayList<>();
	}

	public ICompoundCommand getTransformedCommand() {
		return new ICompoundCommand() {
			@Override
			public Iterator<DriverCommand> iterator() {
				return commands.iterator();
			}

			@Override
			public void execute(Job2dDriver driver) {
				commands.forEach(c -> c.execute(driver));
			}
		};
	}

	@Override
	public void visit(OperateToCommand driver) {
		int x = driver.getPosX();
		int y = driver.getPosY();
		int newX = (int) (x * Math.cos(angle) - y * Math.sin(angle));
		int newY = (int) (x * Math.sin(angle) + y * Math.cos(angle));

		DriverCommand transformedCommand = new OperateToCommand(newX, newY);
		commands.add(transformedCommand);
	}

	@Override
	public void visit(SetPositionCommand driver) {
		int x = driver.getPosX();
		int y = driver.getPosY();
		int newX = (int) (x * Math.cos(angle) - y * Math.sin(angle));
		int newY = (int) (x * Math.sin(angle) + y * Math.cos(angle));

		SetPositionCommand transformedCommand = new SetPositionCommand(newX, newY);
		commands.add(transformedCommand);
	}

	@Override
	public void visit(ICompoundCommand driver) {
		for (Iterator<DriverCommand> i = driver.iterator(); i.hasNext(); ) {
			i.next().accept(this);
		}
	}
}
