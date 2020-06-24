package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CommandCounterVisitor;
import edu.kis.powp.jobs2d.command.CommandLengthVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.logging.Logger;

public class CalculateStatisticListener implements ActionListener {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void actionPerformed(ActionEvent e) {

		DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
		if (command == null) {
			logger.info("Command is not loaded.");
			return;
		}

		CommandCounterVisitor commandCounterVisitor = new CommandCounterVisitor();
		CommandLengthVisitor commandLengthVisitor = new CommandLengthVisitor();
		command.accept(commandCounterVisitor);
		command.accept(commandLengthVisitor);

		logger.info("Statistics of command :\nProgram command executed : " +commandCounterVisitor.getAllCommandsCounter()
			+ " operations. \nTotal length of command was: "  +  new DecimalFormat("#0.000").format( commandLengthVisitor.getLength()));
	}
}
