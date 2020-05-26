package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandTransform implements DriverCommandVisitor {
    protected DriverCommand transformed = null;

    @Override
    public void visit(ICompoundCommand driverCommand) {
        List<DriverCommand> list = new ArrayList<>();

        for (DriverCommand partOfCompoundCommand : driverCommand) {
            partOfCompoundCommand.accept(this);
            list.add(transformed);
        }

        transformed = new DefaultCompoundCommand(list);
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        transformed = new OperateToCommand(driverCommand.getX(), driverCommand.getY());
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        transformed = new SetPositionCommand(driverCommand.getX(), driverCommand.getY());
    }
}
