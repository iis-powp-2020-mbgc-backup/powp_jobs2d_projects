package edu.kis.powp.jobs2d.command;

import java.util.Iterator;
import java.util.logging.Logger;

public class CommandCounterVisitor implements Visitor {

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
        operateToCommandCounter++;
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        setPositionCommandCounter++;
    }

    @Override
    public void visit(ICompoundCommand driverCommand) {
        Iterator<DriverCommand> iterator = driverCommand.iterator();
        while (iterator.hasNext())
        {
            DriverCommand driverCommand1 = iterator.next();
            driverCommand1.accept(this);
        }
    }
}
