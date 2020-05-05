package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class SelectCompoundCommandVisitorTestListener implements ActionListener {
	private Logger logger = Logger.getLogger("global");

	@Override
	public void actionPerformed(ActionEvent e) {

		List<DriverCommand> commands = new ArrayList<>(
				Arrays.asList(
						new SetPositionCommand(0,0),
						new SetPositionCommand(0,0),
						new SetPositionCommand(0,0)
				)
		);

		this.logger.info("Testing visitor for compound command.");
		ICompoundCommand embeddedCompoundCommand = new ICompoundCommand() {
			private List<DriverCommand> embeddedCommands = new ArrayList<>(
					Arrays.asList(
						new SetPositionCommand(0,0),
						new OperateToCommand(0,0)
					)
			);
			@Override
			public Iterator<DriverCommand> iterator() {
				return embeddedCommands.iterator();
			}

			@Override
			public void execute(Job2dDriver driver) {

			}

			@Override
			public DriverCommand clone() throws CloneNotSupportedException {
				throw new CloneNotSupportedException();
			}
		};
		commands.add(embeddedCompoundCommand);
		ICompoundCommand command = new ICompoundCommand() {
			@Override
			public Iterator<DriverCommand> iterator() {
				return commands.iterator();
			}

			@Override
			public void execute(Job2dDriver driver) {

			}

			@Override
			public DriverCommand clone() throws CloneNotSupportedException {
				throw new CloneNotSupportedException();
			}
		};
		DriverCommandCallCounterVisitor visitor = new DriverCommandCallCounterVisitor();
		command.accept(visitor);
		logger.info("Counter: " + visitor.getCounter());//should be 6
	}
}
