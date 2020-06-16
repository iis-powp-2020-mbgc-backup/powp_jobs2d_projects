package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorInterface;

public class CommandUsageCounterVisitor implements CommandVisitorInterface {
    public int operateToCounter = 0;
    public int setPositionCounter = 0;

    public void visit(ICompoundCommand command) {
        for (DriverCommand partOfCompoundCommand : command) {
            partOfCompoundCommand.accept(this);
        }
    }

    public void visit(SetPositionCommand driverCommand) {
        this.setPositionCounter++;
    }

    public void visit(OperateToCommand driverCommand) {
        this.operateToCounter++;
    }

}