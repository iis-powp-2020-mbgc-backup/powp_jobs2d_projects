package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverLevelTransformation;

/**
 * Implementation of Job2dDriverCommand for setPosition command functionality.
 */
public class SetPositionCommand implements DriverCommand {

	private int posX, posY;

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public SetPositionCommand(int posX, int posY, DriverLevelTransformation transformation) {
		super();
		if (transformation == null) {
			new SetPositionCommand(posX, posY);
		} else {
			this.posX = transformation.transformXPoint(posX, posY);
			this.posY = transformation.transformYPoint(posX, posY);
		}
	}

	public SetPositionCommand(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public void execute(Job2dDriver driver) {
		driver.setPosition(posX, posY);
	}

	@Override
  public DriverCommand clone() throws CloneNotSupportedException {
		return (DriverCommand) super.clone();
	}

  @Override
	public void accept(CommandVisitorInterface visitor) {
		visitor.visit(this);
	}
}
