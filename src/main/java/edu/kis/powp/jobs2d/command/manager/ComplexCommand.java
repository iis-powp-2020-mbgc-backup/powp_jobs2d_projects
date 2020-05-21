package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {

    private List<DriverCommand> commandList;

    public ComplexCommand(List<DriverCommand> commands) {
        this.commandList = commands;
    }

    @Override
    public void execute(Job2dDriver driver) {
        for(int i=0;i<commandList.size();i++) {
            commandList.get(i).execute(driver);
        }
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commandList.iterator();
    }
}
