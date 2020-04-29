package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class SelectCommandVisitorTestListener implements ActionListener {
	private List<DriverCommand> commands = Arrays.asList(new SetPositionCommand(0,0), new SetPositionCommand(0,0), new SetPositionCommand(0,0));
	private Logger logger = Logger.getLogger("global");

	@Override
	public void actionPerformed(ActionEvent e) {
		this.logger.info("Testing visitor for compound command.");
		ICompoundCommand command = new ICompoundCommand() {
			@Override
			public Iterator<DriverCommand> iterator() {
				return commands.iterator();
			}

			@Override
			public void execute(Job2dDriver driver) {

			}
		};
		DriverCommandCallCounterVisitor visitor = new DriverCommandCallCounterVisitor();
		command.accept(visitor);
		logger.info("Counter: " + visitor.getCounter());
	}
}
