package edu.kis.powp.jobs2d.command.complexCommandEditor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.builders.ComplexCommandBuilder;

public interface IComplexCommandEditor {

	ComplexCommand getEditedComplexCommand();

	void addCommand(DriverCommand command);

	void deleteCommand(int commandIndex);

	void moveCommandUp(int commandIndex);

	void moveCommandDown(int commandIndex);

	void modifyCoordinates(int commandIndex, int x, int y);
}
