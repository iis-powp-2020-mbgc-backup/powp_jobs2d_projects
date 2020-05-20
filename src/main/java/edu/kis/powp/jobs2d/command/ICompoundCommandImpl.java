package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ICompoundCommandImpl implements ICompoundCommand {

    List<DriverCommand> driverCommands;

    public ICompoundCommandImpl(List<DriverCommand> commandList) {
        this.driverCommands = commandList;
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return driverCommands.iterator();
    }

    @Override
    public ICompoundCommandImpl clone() throws CloneNotSupportedException {
        List<DriverCommand> commands = new ArrayList<>();
        for (DriverCommand command : this.driverCommands) {
            commands.add(command.clone());
        }
        return new ICompoundCommandImpl(commands);
    }

    @Override
    public void execute(Job2dDriver driver) {
        driverCommands.forEach((c) -> c.execute(driver));
    }
}