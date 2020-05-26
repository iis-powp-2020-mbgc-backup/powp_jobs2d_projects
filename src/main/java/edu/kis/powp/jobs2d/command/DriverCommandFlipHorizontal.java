package edu.kis.powp.jobs2d.command;

public class DriverCommandFlipHorizontal extends CommandTransform {
    private int factor = -1;
    public DriverCommandFlipHorizontal() {
    }

    public DriverCommand getFlippedCommand() {
        return transformed;
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        transformed = new OperateToCommand(driverCommand.getX(), driverCommand.getY() * factor);
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        transformed = new SetPositionCommand(driverCommand.getX(), driverCommand.getY() * factor);
    }
}
