package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.visitor.CommandVisitorInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandEditor implements CommandVisitorInterface {

    private DriverCommand command;

    private int x;
    private int y;

    public CommandEditor(DriverCommand command) {
        this.command = command;
    }

    public void edit(int x, int y) {
        this.x = x;
        this.y = y;
        command.accept(this);
    }

    public DriverCommand getCommand() {
        return command;
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        this.command = new OperateToCommand(this.x, this.y);
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        this.command = new SetPositionCommand(this.x, this.y);

    }

    @Override
    public void visit(ICompoundCommand iCompoundCommand) {
        List<DriverCommand> commands = new ArrayList<>();
        for (DriverCommand driverCommand : (ICompoundCommand) command) {
            commands.add(driverCommand);
        }
        if (commands.size() < x) {
            throw new IndexOutOfBoundsException();
        }
        if (commands.size() < y) {
            throw new IndexOutOfBoundsException();
        }
        Collections.swap(commands, x, y);
        command = new ImmutableComplexCommand(commands);
    }
}