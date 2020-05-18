package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
        CommandExecutorVisitor commandCounterVisitor = new CommandExecutorVisitor(this.driver);

        int expectedSetPostitionCalls = 3;
        int expectedOperateToCalls = 2;
        int expectedAllCalls = 5;

        List<DriverCommand> driverCommands = new ArrayList<>();
        driverCommands.add(new SetPositionCommand(-20, -50));
        driverCommands.add(new OperateToCommand(-20, -50));
        driverCommands.add(new SetPositionCommand(-20, -40));
        driverCommands.add(new OperateToCommand(-20, 50));
        driverCommands.add(new SetPositionCommand(0, -50));

        driverCommands.forEach((c) -> c.accept(commandCounterVisitor));

        logger.info("Visitor Command Executor test");
    }
}
