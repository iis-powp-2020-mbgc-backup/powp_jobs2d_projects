package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public CompoundCommand clone() throws CloneNotSupportedException {
        List<DriverCommand> commands = new ArrayList<>();

        for (DriverCommand c : this.commands) {
            commands.add(c.clone());
        }

        return new CompoundCommand(commands, this.name);
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
