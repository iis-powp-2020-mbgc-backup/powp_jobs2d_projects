package edu.kis.powp.jobs2d.command;

public class DriverCommandRotate90degrees extends CommandTransform {
    private Direction dir;
    private int factor = -1;

    public DriverCommandRotate90degrees(Direction dir) {
        this.dir = dir;
    }

    public DriverCommand getRotatedCommand() {
        return transformed;
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        if (dir == Direction.RIGHT) {
            transformed = new OperateToCommand(driverCommand.getY() * factor, driverCommand.getX());
        } else if (dir == Direction.LEFT) {
            transformed = new OperateToCommand(driverCommand.getY(), driverCommand.getX() * factor);
        }
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        if (dir == Direction.RIGHT) {
            transformed = new SetPositionCommand(driverCommand.getY() * factor, driverCommand.getX());
        } else if (dir == Direction.LEFT) {
            transformed = new SetPositionCommand(driverCommand.getY(), driverCommand.getX() * factor);
        }
    }

    public enum Direction {
        LEFT, RIGHT
    }
}
