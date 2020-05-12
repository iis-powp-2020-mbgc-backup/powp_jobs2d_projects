package edu.kis.powp.jobs2d.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SelectSingleCommandVisitorTestListener implements ActionListener {

    private Logger logger = Logger.getLogger("global");

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("Testing visitor for driver command.");
        DriverCommand command = new OperateToCommand(0, 0);
        DriverCommandCallCounterVisitor visitor = new DriverCommandCallCounterVisitor();
        command.accept(visitor);
        logger.info("Counter: " + visitor.getCounter());
    }
}
