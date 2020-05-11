package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ImmutableCompoundCommand implements ICompoundCommand {

    private List<DriverCommand> driverCommands = null;

    public ImmutableCompoundCommand(List<DriverCommand> driverCommands) {

        this.driverCommands = new ArrayList<>();
        for (Object object : driverCommands) {
            if (object instanceof DefaultCompoundCommand) {
                List<DriverCommand> dc = new ArrayList<>();
                for (DriverCommand command : (DefaultCompoundCommand) object) {
                    dc.add(command);
                }
                ImmutableCompoundCommand icc = new ImmutableCompoundCommand(dc);
                this.driverCommands.add(icc);

            }else{
                this.driverCommands.add((DriverCommand) object);
            }
        }
        this.driverCommands = Collections.unmodifiableList(this.driverCommands);

    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return this.driverCommands.iterator();
    }

    @Override
    public void execute(Job2dDriver driver) {
        this.driverCommands.forEach(c -> c.execute(driver));
    }

    @Override
    public DriverCommand clone() throws CloneNotSupportedException {
        return ICompoundCommand.super.clone();
    }
}
