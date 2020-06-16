package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.CommandDrawerPattern.CommandList;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.features.CommandsFeature;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class DriverCommandCounterVisitorTest implements ActionListener {

	private Job2dDriver driver;

	public DriverCommandCounterVisitorTest(Job2dDriver driver)
	{
		this.driver = driver;
	}

	public DriverCommandCounterVisitorTest() {

	}

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		logger.info("Testing Command Counter Visitor");
		ComprisingCommandsCounterVisitor commandCounterVisitor = new ComprisingCommandsCounterVisitor(this.driver);

		DriverCommand driverCommand = CommandsFeature.getDriverCommandManager().getCurrentCommand();
		driverCommand.accept(commandCounterVisitor);

		logger.info("Visitor Command Counting\n SetPosition counter: " + commandCounterVisitor.getSetPositionCounter() + " OperateTo counter: "
						+ commandCounterVisitor.getOperateToCounter());
	}
}
