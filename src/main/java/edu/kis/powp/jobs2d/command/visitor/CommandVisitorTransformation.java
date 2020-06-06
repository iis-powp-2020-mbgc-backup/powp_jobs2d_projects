package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.transformation.Transformation;

import java.util.ArrayList;
import java.util.List;

public class CommandVisitorTransformation implements CommandVisitorInterface {
    private final List<DriverCommand> driverCommandList;
    private final Transformation transformation;

    public CommandVisitorTransformation(Transformation transformation) {
        this.driverCommandList = new ArrayList<>();
        this.transformation = transformation;
    }

    public ICompoundCommand getCompoundCommand() {
        return new DefaultCompoundCommand(this.driverCommandList);
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        int transformedXPoint = this.transformation.transformXPoint(setPositionCommand.getPosX(), setPositionCommand.getPosY());
        int transformedYPoint = this.transformation.transformYPoint(setPositionCommand.getPosX(), setPositionCommand.getPosY());

        this.driverCommandList.add(new SetPositionCommand(transformedXPoint, transformedYPoint));
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        int transformedXPoint = this.transformation.transformXPoint(operateToCommand.getPosX(), operateToCommand.getPosY());
        int transformedYPoint = this.transformation.transformYPoint(operateToCommand.getPosX(), operateToCommand.getPosY());

        this.driverCommandList.add(new OperateToCommand(transformedXPoint, transformedYPoint));
    }

    @Override
    public void visit(ICompoundCommand iCompoundCommand){
        for (DriverCommand driverCommand : iCompoundCommand) {
            driverCommand.accept(this);
        }
    }
}
