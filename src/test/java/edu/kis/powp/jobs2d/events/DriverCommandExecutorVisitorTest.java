package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.CommandExecutorVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class DriverCommandExecutorVisitorTest implements ActionListener
{
	private Job2dDriver driver;

	public DriverCommandExecutorVisitorTest(Job2dDriver driver)
	{
		this.driver = driver;
	}

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		logger.info("Testing Command Executor Visitor");
		CommandExecutorVisitor commandExecutorVisitor = new CommandExecutorVisitor(this.driver);

		DriverCommand driverCommand = CommandsFeature.getDriverCommandManager().getCurrentCommand();
		driverCommand.accept(commandExecutorVisitor);

		logger.info("Visitor Command Executor test");
	}
}
