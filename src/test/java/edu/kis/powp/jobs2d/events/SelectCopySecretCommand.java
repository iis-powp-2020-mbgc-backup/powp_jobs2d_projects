package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectCopySecretCommand implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		DefaultCompoundCommand compound = null;
		List<DriverCommand> commands = new ArrayList<DriverCommand>();
		DriverCommand first, second, third, lastOne; // test DriverCommands

		try {
			commands.add(new SetPositionCommand(-20, -50));
			commands.add(new OperateToCommand(-20, -50));
			commands.add(new SetPositionCommand(-20, -40));
			compound = new DefaultCompoundCommand(commands);
			first = CommandsFeature.deepCopyCommand(compound);

			commands.clear();
			commands.add(new OperateToCommand(-20, 50));
			commands.add(new SetPositionCommand(0, -50));
			commands.add(new OperateToCommand(0, -50));
			commands.add(new SetPositionCommand(0, -40));
			commands.add(new OperateToCommand(0, 50));
			compound = new DefaultCompoundCommand(commands);
			second = CommandsFeature.deepCopyCommand(compound);

			commands.clear();
			commands.add(new SetPositionCommand(70, -50));
			commands.add(new OperateToCommand(20, -50));
			commands.add(new OperateToCommand(20, 0));
			commands.add(new OperateToCommand(70, 0));
			commands.add(new OperateToCommand(70, 50));
			commands.add(new OperateToCommand(20, 50));
			compound = new DefaultCompoundCommand(commands);
			third = CommandsFeature.deepCopyCommand(compound);

			commands.clear();
			commands.add(first);
			commands.add(second);
			compound = new DefaultCompoundCommand(commands);
			first = CommandsFeature.deepCopyCommand(compound);

			commands.clear();
			commands.add(first);
			commands.add(third);
			compound = new DefaultCompoundCommand(commands);
			lastOne = CommandsFeature.deepCopyCommand(compound);

			commands.clear();
			commands.add(lastOne);
		} catch (CloneNotSupportedException exc) {
			Logger.getLogger("global").log(Level.WARNING, exc.toString());
			return;
		}

		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(commands, "CopiedTopSecretCommand");
	}
}
