package edu.kis.powp.jobs2d.command;

public class DriverCommandFlipVertical extends CommandTransform {
    private int factor = -1;

    public DriverCommandFlipVertical() {
    }

    public DriverCommand getFlippedCommand() {
        return transformed;
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        transformed = new OperateToCommand(driverCommand.getX() * factor, driverCommand.getY());
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        transformed = new SetPositionCommand(driverCommand.getX() * factor, driverCommand.getY());
    }
}
