package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DriverCommandCounterVisitorTest implements ActionListener {

    private Job2dDriver driver;

    public DriverCommandCounterVisitorTest(Job2dDriver driver)
    {
        this.driver = driver;
    }

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        logger.info("Testing Command Visitor");
        ComprisingCommandsCounterVisitor commandCounterVisitor = new ComprisingCommandsCounterVisitor(this.driver);

        int expectedSetPostitionCalls = 3;
        int expectedOperateToCalls = 2;
        int expectedAllCalls = 5;

        List<DriverCommand> driverCommands = new ArrayList<>();
        driverCommands.add(new SetPositionCommand(-20, -50));
        driverCommands.add(new OperateToCommand(-20, -50));
        driverCommands.add(new SetPositionCommand(-20, -40));
        driverCommands.add(new OperateToCommand(-20, 50));
        driverCommands.add(new SetPositionCommand(0, -50));

        //driverCommands.forEach((c) -> c.accept(commandCounterVisitor));

        if (expectedOperateToCalls == commandCounterVisitor.getOperateToCounter() && expectedSetPostitionCalls == commandCounterVisitor.getSetPositionCounter()
                && expectedAllCalls == commandCounterVisitor.getAllCommandsCounter()) {
            logger.info("Visitor Command Counting test Passed\n Commands counter:" + commandCounterVisitor.getAllCommandsCounter());
        } else {
            logger.info("Visitor Command Counting test Failed" + commandCounterVisitor.getAllCommandsCounter());
        }
    }
}
