package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.List;

public class ICompoundCommandInstance implements ICompoundCommand {

    List<DriverCommand> driverCommands;

    public ICompoundCommandInstance(List<DriverCommand> commandList) {
        this.driverCommands = commandList;
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return driverCommands.iterator();
    }

    @Override
    public ICompoundCommandInstance clone() throws CloneNotSupportedException {
        List<DriverCommand> list = List.copyOf(driverCommands);
        return new ICompoundCommandInstance(list);
    }

    @Override
    public void execute(Job2dDriver driver) {
        driverCommands.forEach((c) -> c.execute(driver));
    }
}
