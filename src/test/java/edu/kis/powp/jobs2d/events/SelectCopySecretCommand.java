package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectCopySecretCommand implements ActionListener {

	private static ICompoundCommand compound = new ICompoundCommand() {

		List<DriverCommand> driverCommands = null;

		@Override
		public void execute(Job2dDriver driver) {
			driverCommands.forEach((c) -> c.execute(driver));
		}

		@Override
		public Iterator<DriverCommand> iterator() {
			return driverCommands.iterator();
		}

		@Override
		public DriverCommand clone() throws CloneNotSupportedException {
			return (DriverCommand) super.clone();
		}

		@Override
		public void setCommands(List<DriverCommand> driverCommands) {
			this.driverCommands = driverCommands;
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {

		DriverCommand first, second, third; // test DriverCommands

		List<DriverCommand> commands = new ArrayList<DriverCommand>();
		commands.add(new SetPositionCommand(-20, -50));
		commands.add(new OperateToCommand(-20, -50));
		commands.add(new SetPositionCommand(-20, -40));
		compound.setCommands(commands);
		first = CommandCopier.copyCommand(compound);

		commands.clear();
		commands.add(new OperateToCommand(-20, 50));
		commands.add(new SetPositionCommand(0, -50));
		commands.add(new OperateToCommand(0, -50));
		commands.add(new SetPositionCommand(0, -40));
		commands.add(new OperateToCommand(0, 50));
		compound.setCommands(commands);
		second = CommandCopier.copyCommand(compound);

		commands.clear();
		commands.add(new SetPositionCommand(70, -50));
		commands.add(new OperateToCommand(20, -50));
		commands.add(new OperateToCommand(20, 0));
		commands.add(new OperateToCommand(70, 0));
		commands.add(new OperateToCommand(70, 50));
		commands.add(new OperateToCommand(20, 50));
		compound.setCommands(commands);
		third = CommandCopier.copyCommand(compound);

		List<DriverCommand> toCopy = new ArrayList<>();
		toCopy.add(first);
		toCopy.add(second);
		toCopy.add(third);

		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(toCopy, "CopiedTopSecretCommand");
	}
}
