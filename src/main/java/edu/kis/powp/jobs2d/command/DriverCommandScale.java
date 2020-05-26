package edu.kis.powp.jobs2d.command;

public class DriverCommandScale extends CommandTransform {
    private float scaleX;
    private float scaleY;

    public DriverCommandScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public DriverCommandScale(float scale) {
        this(scale, scale);
    }

    public DriverCommand getScaledCommand() {
        return transformed;
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        transformed = new OperateToCommand((int) (driverCommand.getX() * scaleX), (int) (driverCommand.getY() * scaleY));
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        transformed = new SetPositionCommand((int) (driverCommand.getX() * scaleX), (int) (driverCommand.getY() * scaleY));
    }
}
