package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class CommandVisitor implements ICommandVisitor {

    private Job2dDriver job2dDriver;

    public CommandVisitor(Job2dDriver job2dDriver) {
        this.job2dDriver = job2dDriver;
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        setPositionCommand.execute(this.job2dDriver);
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        operateToCommand.execute(this.job2dDriver);
    }

    @Override
    public void visit(ICompoundCommand compoundCommand) {
        compoundCommand.execute(this.job2dDriver);
    }
}
