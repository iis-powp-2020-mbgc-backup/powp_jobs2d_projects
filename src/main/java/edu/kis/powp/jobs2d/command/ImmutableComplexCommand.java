package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ImmutableComplexCommand implements ICompoundCommand {
	private final List<DriverCommand> driverCommands;

	ImmutableComplexCommand(final Collection<? extends DriverCommand> driverCommands) {
		this.driverCommands = Collections.unmodifiableList(new ArrayList<DriverCommand>(driverCommands));
	}

	@Override
	public DriverCommand clone() throws CloneNotSupportedException {
		return ICompoundCommand.super.clone();
	}

	@Override
	public void execute(final Job2dDriver driver) {
		for (final DriverCommand driverCommand : driverCommands) {
			driverCommand.execute(driver);
		}
	}

	@Override
	public Iterator<DriverCommand> iterator() {
		return driverCommands.iterator();
	}
}