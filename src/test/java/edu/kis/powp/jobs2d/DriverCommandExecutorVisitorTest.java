package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.CommandDrawerPattern.CommandList;
import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
		logger.info("Testing Command Visitor");
		CommandExecutorVisitor commandExecutorVisitor = new CommandExecutorVisitor(this.driver);
		
		DriverCommand driverCommand = CommandsFeature.getDriverCommandManager().getCurrentCommand();
		driverCommand.accept(commandExecutorVisitor);

		logger.info("Visitor Command Executor test");
	}
}
