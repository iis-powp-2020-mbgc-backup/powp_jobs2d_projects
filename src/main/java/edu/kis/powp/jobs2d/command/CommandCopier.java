package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandCopier {
	public static DriverCommand copyCommand(DriverCommand toCopy) {

		DriverCommand copy = null;

		try {
			copy = toCopy.clone();
			if (toCopy instanceof ICompoundCommand) {
				Iterator<DriverCommand> iter = ((ICompoundCommand) toCopy).iterator();
				List<DriverCommand> commandList = new ArrayList<>();
				while (iter.hasNext()) {
					commandList.add(iter.next().clone());
				}
				((ICompoundCommand) copy).setCommands(commandList);
			}
		} catch (CloneNotSupportedException exc) {
			System.out.println(exc);
		}
		return copy;
	}
}
