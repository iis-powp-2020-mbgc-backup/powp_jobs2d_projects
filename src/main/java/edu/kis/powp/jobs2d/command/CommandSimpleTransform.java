package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;

public class CommandSimpleTransform implements DriverCommandVisitor {
    protected DriverCommand transformed = null;

    private float factorX = 1f;
    private float factorY = 1f;

    public void flipHorizontal() {
        factorY *= -1;
    }

    public void flipVertical() {
        factorX *= -1;
    }

    public void scale(float scaleX, float scaleY) {
        factorX *= scaleX;
        factorY *= scaleY;
    }

    public DriverCommand getTransformed() {
        return transformed;
    }

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
        transformed = new OperateToCommand((int) (driverCommand.getX() * factorX), (int) (driverCommand.getY() * factorY));
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        transformed = new SetPositionCommand((int) (driverCommand.getX() * factorX), (int) (driverCommand.getY() * factorY));
    }
}
