package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CompoundCommand implements ICompoundCommand {
    private List<DriverCommand> commands;
    private String name;

    public CompoundCommand(List<DriverCommand> commands) {
        this.commands = commands;
        this.name = "unknown command";
    }

    public CompoundCommand(List<DriverCommand> commands, String name) {
        this.commands = commands;
        this.name = name;
    }

    @Override
    public DriverCommand clone() throws CloneNotSupportedException {
        return ICompoundCommand.super.clone();
    }

    @Override
    public ICompoundCommand moveUpCommand(DriverCommand command) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).equals(command)) {
                if (i > 0) {
                    Collections.swap(commands, i, i - 1);
                }
            }
        }
        return this;
    }

    @Override
    public ICompoundCommand moveDownCommand(DriverCommand command) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).equals(command)) {
                if (i < commands.size() - 1) {
                    Collections.swap(commands, i, i + 1);
                }
                break;
            }
        }
        return this;
    }

    @Override
    public ICompoundCommand changeCoordinates(DriverCommand commandToChange, DriverCommand newCommand) {
        Collections.replaceAll(commands, commandToChange, newCommand);
        return this;
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public void execute(Job2dDriver driver) {
        commands.forEach((command) -> command.execute(driver));
    }

    @Override
    public String toString() {
        return "CompoundCommand name: " + this.name;
    }
}
