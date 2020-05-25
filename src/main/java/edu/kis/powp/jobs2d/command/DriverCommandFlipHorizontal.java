package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;

public class DriverCommandFlipHorizontal implements DriverCommandVisitor {

    private DriverCommand flippedCommand = null;

    public DriverCommand getFlippedCommand() {
        return flippedCommand;
    }

    @Override
    public void visit(ICompoundCommand driverCommand) {
        List<DriverCommand> list = new ArrayList<>();

        for (DriverCommand partOfCompoundCommand : driverCommand) {
            partOfCompoundCommand.accept(this);
            list.add(flippedCommand);
        }

        flippedCommand = new DefaultCompoundCommand(list);
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        flippedCommand = new OperateToCommand(driverCommand.getX(), driverCommand.getY() * (-1));
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        flippedCommand = new SetPositionCommand(driverCommand.getX(), driverCommand.getY() * (-1));
    }
}
