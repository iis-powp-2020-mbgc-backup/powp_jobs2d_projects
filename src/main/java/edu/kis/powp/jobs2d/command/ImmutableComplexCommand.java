package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ImmutableComplexCommand implements ICompoundCommand {

    private final List<DriverCommand> driverCommandList;
    private final String commandName;

    public ImmutableComplexCommand(List<DriverCommand> driverCommandList, String commandName) throws ImmutableCommandCreationException {
        List<DriverCommand> commands = new ArrayList<>();
        this.commandName = commandName;
        for (DriverCommand command : driverCommandList) {
            try {
                commands.add(command.clone());
            } catch (CloneNotSupportedException e) {
                throw new ImmutableCommandCreationException(e);
            }
        }
        this.driverCommandList = Collections.unmodifiableList(commands);
    }

    public ImmutableComplexCommand(List<DriverCommand> driverCommandList) throws ImmutableCommandCreationException {
        this(driverCommandList, "Unknown immutable command");
    }

    @Override
    public void execute(Job2dDriver driver) {
        for (DriverCommand c : this.driverCommandList) {
            c.execute(driver);
        }
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return driverCommandList.iterator();
    }

    @Override
    public ImmutableComplexCommand clone() throws CloneNotSupportedException {
        List<DriverCommand> commands = new ArrayList<>();
        for (DriverCommand command : this.driverCommandList) {
            commands.add(command.clone());
        }
        ImmutableComplexCommand immutableComplexCommand = null;
        try {
            immutableComplexCommand = new ImmutableComplexCommand(commands, commandName);
        } catch (ImmutableCommandCreationException e) {
            System.out.println(e.getMessage());
        }
        return immutableComplexCommand;
    }

    @Override
    public String toString() {
        return "ImmutableComplexCommand commandName=" + commandName;
    }
}
