package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.CommandStatisticVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.features.DriverFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CommandCounterVisitorTest_drawTriangle implements ActionListener {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		logger.info("Drawing triangle and counting operations...");
		CommandStatisticVisitor commandCounterVisitor = new CommandStatisticVisitor();
		Job2dDriver job2dDriver = DriverFeature.getDriverManager().getCurrentDriver();
		int expectedNumberOfSetPositionMethodCall = 1;
		int expectedNumberOfOperateMethodCall = 3;
		int expectedNumberOfAllOperationsCall = 4;

		List<DriverCommand> driverCommands = new ArrayList<>();
		driverCommands.add(new SetPositionCommand(-50, 0));
		driverCommands.add(new OperateToCommand(0, 50));
		driverCommands.add(new OperateToCommand(50, 0));
		driverCommands.add(new OperateToCommand(-50, 0));
		for (DriverCommand driverCommand : driverCommands) {
			driverCommand.execute(job2dDriver);
			driverCommand.accept(commandCounterVisitor);
		}

		DecimalFormat dec = new DecimalFormat("#0.000");
		if (expectedNumberOfOperateMethodCall == commandCounterVisitor.getOperateToCommandCounter() && expectedNumberOfSetPositionMethodCall == commandCounterVisitor.getSetPositionCommandCounter()
			&& expectedNumberOfAllOperationsCall == commandCounterVisitor.getAllCommandsCounter()) {
			logger.info("Statistics of command :\nProgram command executed : " + dec.format(commandCounterVisitor.getAllCommandsCounter())
				+ " operations. \nTotal length of command was: "  +  dec.format( commandCounterVisitor.getTotalLengthCommand()));
		} else {
			logger.info("Problem with counting operations, check ICompound Command Visitor Test");
		}
	}
}
