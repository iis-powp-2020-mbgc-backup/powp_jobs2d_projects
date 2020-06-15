package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.CommandCounterVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CommandCounterVisitorTest_drawTriangle implements ActionListener {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		logger.info("Drawing triangle and counting operations...");
		CommandCounterVisitor commandCounterVisitor = new CommandCounterVisitor();
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

		if (expectedNumberOfOperateMethodCall == commandCounterVisitor.getOperateToCommandCounter() && expectedNumberOfSetPositionMethodCall == commandCounterVisitor.getSetPositionCommandCounter()
			&& expectedNumberOfAllOperationsCall == commandCounterVisitor.getAllCommandsCounter()) {
			logger.info("While drawing a triangle program executed: " + expectedNumberOfAllOperationsCall + " operations");
		} else {
			logger.info("Problem with counting operations, check Driver Command Visitor Test");
		}
	}
}
