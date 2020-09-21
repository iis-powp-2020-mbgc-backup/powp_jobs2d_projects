package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

public class CommandCoordinateChangerVisitor implements CommandVisitorInterface {
    private DriverCommand command;
    private int x;
    private int y;

    public DriverCommand changeCoordinates(DriverCommand command, int x, int y) {
        this.x = x;
        this.y = y;
        command.accept(this);
        return this.command;
    }

    @Override
    public void visit(OperateToCommand driver) {
        command = new OperateToCommand(this.x, this.y);
    }

    @Override
    public void visit(SetPositionCommand driver) {
        command = new SetPositionCommand(this.x, this.y);
    }

    @Override
    public void visit(ICompoundCommand driver) {
        command = driver;
    }
}