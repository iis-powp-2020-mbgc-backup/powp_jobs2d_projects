package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CommandStatisticVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.logging.Logger;

public class CalculateStatisticVisitorListener implements ActionListener {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void actionPerformed(ActionEvent e) {

		DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
		if (command == null) {
			logger.info("Command is not loaded.");
			return;
		}
		CommandStatisticVisitor commandCounterVisitor = new CommandStatisticVisitor();
		command.accept(commandCounterVisitor);

		DecimalFormat dec = new DecimalFormat("#0.000");
		logger.info("Statistics of command :\nProgram command executed : " + dec.format(commandCounterVisitor.getAllCommandsCounter())
			+ " operations. \nTotal length of command was: "  +  dec.format( commandCounterVisitor.getTotalLengthCommand()));
	}
}
