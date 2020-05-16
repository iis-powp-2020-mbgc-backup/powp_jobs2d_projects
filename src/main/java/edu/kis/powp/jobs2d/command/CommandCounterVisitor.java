package edu.kis.powp.jobs2d.command;

import java.util.Iterator;
import java.util.logging.Logger;

public class CommandCounterVisitor implements Visitor {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private int operateToCommandCounter = 0;
    private int setPositionCommandCounter = 0;

    public int getOperateToCommandCounter() {
        return operateToCommandCounter;
    }

    public int getSetPositionCommandCounter() {
        return setPositionCommandCounter;
    }
    public int getAllCommandCounter() {
        return setPositionCommandCounter + operateToCommandCounter;
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        setPositionCommandCounter++;
        logger.info("Invoke OperateToCommand command " + setPositionCommandCounter +" time");
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        operateToCommandCounter++;
        logger.info("Invoke SetPosition command " + operateToCommandCounter +" time");
    }

    @Override
    public void visit(ICompoundCommand driverCommand) {
        Iterator<DriverCommand> iterator = driverCommand.iterator();
        while (iterator.hasNext())
        {
            DriverCommand driverCommand1 = iterator.next();
            driverCommand1.accept(this);
        }
        logger.info("Invoke all commands " + getAllCommandCounter() +" time");
    }
}
