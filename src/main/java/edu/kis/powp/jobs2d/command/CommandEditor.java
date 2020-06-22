package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandEditor {

    private DriverCommand command;

    CommandEditor(DriverCommand command) {
        this.command = command;
    }

    public void changeCoordinates(int x, int y) throws ImproperCommandTypeException {
        if (command instanceof ICompoundCommand) {
            throw new ImproperCommandTypeException();
        }
        if (command instanceof SetPositionCommand) {
            command = new SetPositionCommand(x, y);
        } else if (command instanceof OperateToCommand) {
            command = new OperateToCommand(x, y);
        }
    }

    public void changeOrder(int previousIndex, int newIndex) throws ImproperCommandTypeException {
        if (!(command instanceof ICompoundCommand)) {
            throw new ImproperCommandTypeException();
        }
        List<DriverCommand> commands = new ArrayList<>();
        for (DriverCommand driverCommand : (ICompoundCommand) command) {
            commands.add(driverCommand);
        }
        if (commands.size() < previousIndex) {
            throw new IndexOutOfBoundsException();
        }
        if (commands.size() < newIndex) {
            throw new IndexOutOfBoundsException();
        }
        Collections.swap(commands, previousIndex, newIndex);
        command = new ImmutableComplexCommand(commands);
    }

    public DriverCommand getCommand() {
        return command;
    }
}
