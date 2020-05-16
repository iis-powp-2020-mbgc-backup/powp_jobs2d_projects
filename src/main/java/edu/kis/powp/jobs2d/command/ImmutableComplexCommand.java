package edu.kis.powp.jobs2d.command;

import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ImmutableComplexCommand implements ICompoundCommand {
    private List<DriverCommand> driverCommandList;

    public ImmutableComplexCommand(List<DriverCommand> driverCommandList) {
        this.driverCommandList = driverCommandList;
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

}
