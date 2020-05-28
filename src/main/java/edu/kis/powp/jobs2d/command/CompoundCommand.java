package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.List;

public class CompoundCommand implements ICompoundCommand {
    private List<DriverCommand> commands;

    public CompoundCommand(List<DriverCommand> commands) {
        this.commands = commands;
    }

    @Override
    public DriverCommand clone() throws CloneNotSupportedException {
        return ICompoundCommand.super.clone();
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public void execute(Job2dDriver driver) {
        commands.forEach((command) -> command.execute(driver));
    }
}
