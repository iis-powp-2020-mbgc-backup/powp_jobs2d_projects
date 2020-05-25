package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;

public class DriverCommandScale implements DriverCommandVisitor {

    private DriverCommand scaledCommand = null;
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
        return scaledCommand;
    }

    @Override
    public void visit(ICompoundCommand driverCommand) {
        List<DriverCommand> list = new ArrayList<>();

        for (DriverCommand partOfCompoundCommand : driverCommand) {
            partOfCompoundCommand.accept(this);
            list.add(scaledCommand);
        }

        scaledCommand = new DefaultCompoundCommand(list);
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        scaledCommand = new OperateToCommand((int)(driverCommand.getX() * scaleX), (int)(driverCommand.getY() * scaleY));
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        scaledCommand = new SetPositionCommand((int)(driverCommand.getX() * scaleX), (int)(driverCommand.getY() * scaleY));
    }
}
