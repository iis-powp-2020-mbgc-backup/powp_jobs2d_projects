package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.*;

import edu.kis.powp.jobs2d.features.DriverFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class ICompoundCommandVisitorTest_drawLock implements ActionListener {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		logger.info("Drawing lock and counting operations...");
		CommandCounterVisitor commandCounterVisitor = new CommandCounterVisitor();
		Job2dStub job2dStub = new Job2dStub();
		Job2dDriver job2dDriver = DriverFeature.getDriverManager().getCurrentDriver();

		int expectedNumberOfSetPositionMethodCall = 2;
		int expectedNumberOfOperateMethodCall = 9;
		int expectedNumberOfAllOperationsCall = 11;

		List<DriverCommand> commands = new ArrayList<>();
		commands.add(new SetPositionCommand(10, -10));
		commands.add(new OperateToCommand(10,0));
		commands.add(new OperateToCommand(0, 10));
		commands.add(new OperateToCommand(-10,0));
		commands.add(new OperateToCommand(-10,-10));
		commands.add(new OperateToCommand(10,-10));
		commands.add(new SetPositionCommand(100, -100));
		commands.add(new OperateToCommand(100,100));
		commands.add(new OperateToCommand(-100,100));
		commands.add(new OperateToCommand(-100,-100));
		commands.add(new OperateToCommand(100,-100));

		ICompoundCommand iCompoundCommand = new ICompoundCommand() {

			List<DriverCommand> driverCommands = commands;
			String name;

			@Override
			public void accept(Visitor visitor) {
				visitor.visit(this);
			}

			@Override
			public void execute(Job2dDriver driver) {
				driverCommands.forEach((c) -> c.execute(driver));
			}

			@Override
			public Iterator<DriverCommand> iterator() {
				return driverCommands.iterator();
			}

			@Override
			public ICompoundCommand clone() throws CloneNotSupportedException {
				List<DriverCommand> commands = new ArrayList<>();
				for (DriverCommand command : this.driverCommands) {
					commands.add(command.clone());
				}
				return new ComplexCommand(commands);
			}

			@Override
			public String toString() {
				return name;
			}
		};

		CommandCounterVisitor commandCounter = commandCounterVisitor;
		iCompoundCommand.accept(commandCounter);
		iCompoundCommand.execute(job2dDriver);

		if (expectedNumberOfOperateMethodCall == commandCounterVisitor.getOperateToCommandCounter() && expectedNumberOfSetPositionMethodCall == commandCounterVisitor.getSetPositionCommandCounter()
			&& expectedNumberOfAllOperationsCall == commandCounterVisitor.getAllCommandsCounter()) {
			logger.info("While drawing a triangle program executed: " + expectedNumberOfAllOperationsCall + " operations");
		} else {
			logger.info("Problem with counting operations, check ICompound Command Visitor Test");
		}

	}

	class Job2dStub implements Job2dDriver{

		@Override
		public void setPosition(int i, int i1) {
			logger.info("Set position to " + i + " " + i1);
		}

		@Override
		public void operateTo(int i, int i1) {
			logger.info("Operate to " + i + " " + i1);
		}

	}
}
