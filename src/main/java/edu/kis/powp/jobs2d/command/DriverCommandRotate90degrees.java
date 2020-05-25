package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;

public class DriverCommandRotate90degrees implements DriverCommandVisitor {

    private DriverCommand rotatedCommand = null;
    private Direction dir;

    public DriverCommandRotate90degrees(Direction dir) {
        this.dir = dir;
    }

    public DriverCommand getRotatedCommand() {
        return rotatedCommand;
    }

    @Override
    public void visit(ICompoundCommand driverCommand) {
        List<DriverCommand> list = new ArrayList<>();

        for (DriverCommand partOfCompoundCommand : driverCommand) {
            partOfCompoundCommand.accept(this);
            list.add(rotatedCommand);
        }

        rotatedCommand = new DefaultCompoundCommand(list);
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        if (dir == Direction.RIGHT) {
            rotatedCommand = new OperateToCommand(driverCommand.getY() * (-1), driverCommand.getX());
        } else if (dir == Direction.LEFT) {
            rotatedCommand = new OperateToCommand(driverCommand.getY(), driverCommand.getX() * (-1));
        }
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        if (dir == Direction.RIGHT) {
            rotatedCommand = new SetPositionCommand(driverCommand.getY() * (-1), driverCommand.getX());
        } else if (dir == Direction.LEFT) {
            rotatedCommand = new SetPositionCommand(driverCommand.getY(), driverCommand.getX() * (-1));
        }
    }

    public enum Direction {
        LEFT, RIGHT
    }
}
