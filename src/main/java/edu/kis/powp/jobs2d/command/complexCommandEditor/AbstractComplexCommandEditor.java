package edu.kis.powp.jobs2d.command.complexCommandEditor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.builders.ComplexCommandBuilder;

public abstract class AbstractComplexCommandEditor {

	ICompoundCommand complexCommand;
	ComplexCommandBuilder complexCommandBuilder;


	public abstract ComplexCommand getEditedComplexCommand();

	public abstract void addCommand(DriverCommand command);

	public abstract void deleteCommand(int commandIndex);

	public abstract void moveCommandUp(int commandIndex);

	public abstract void moveCommandDown(int commandIndex);

	public abstract void modifyCoordinates(int commandIndex, int x, int y);
}
