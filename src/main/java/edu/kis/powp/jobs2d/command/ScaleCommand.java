package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScaleCommand implements CommandVisitorInterface {
	private List<DriverCommand> commands;
	private double scaleX;
	private double scaleY;

	public ScaleCommand(double scaleX, double scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;

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
		int newX = (int) (x * scaleX);
		int newY = (int) (y * scaleY);

		DriverCommand transformedCommand = new OperateToCommand(newX, newY);
		commands.add(transformedCommand);
	}

	@Override
	public void visit(SetPositionCommand driver) {
		int x = driver.getPosX();
		int y = driver.getPosY();
		int newX = (int) (x * scaleX);
		int newY = (int) (y * scaleY);

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
