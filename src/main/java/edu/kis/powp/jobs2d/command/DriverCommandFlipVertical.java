package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class DriverCommandFlipVertical implements DriverCommandVisitor {

    private DriverCommand flippedTable = null;

    public DriverCommand getFlippedTable() {
        return flippedTable;
    }

    @Override
    public void visit(ICompoundCommand driverCommand) {
        for (DriverCommand partOfCompoundCommand : driverCommand) {
            partOfCompoundCommand.accept(this);
        }
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {

    }
}
