package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ImmutableComplexCommand implements ICompoundCommand {

    private List<DriverCommand> driverCommandList;
    private String commandName;

    public ImmutableComplexCommand(List<DriverCommand> driverCommandList, String commandName) {
        this.driverCommandList = Collections.unmodifiableList(driverCommandList);
        this.commandName = commandName;
    }

    public ImmutableComplexCommand(List<DriverCommand> driverCommandList) {
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
        return new ImmutableComplexCommand(commands, commandName);
    }

    @Override
    public String toString() {
        return "ImmutableComplexCommand commandName=" + commandName;
    }
}
