package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultCompoundCommand implements ICompoundCommand {

    private List<DriverCommand> listOfDriverCommand;
    private String name;

    public DefaultCompoundCommand(List<DriverCommand> collection) {
        this.listOfDriverCommand = collection;
    }

    public DefaultCompoundCommand(List<DriverCommand> collection, String name) {
        this.listOfDriverCommand = collection;
        this.name = name;
    }

    @Override
    public void execute(Job2dDriver driver) {
        for (DriverCommand c : listOfDriverCommand) {
            c.execute(driver);
        }
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return listOfDriverCommand.iterator();
    }

    @Override
    public DriverCommand clone( ) throws CloneNotSupportedException{
        List<DriverCommand> commands = new ArrayList<>();
        for (DriverCommand command : this) {
            commands.add(command.clone());
        }
        return new DefaultCompoundCommand(commands, this.name);
    }

    @Override
    public String toString(){
        return this.name == null ? this.toString() : this.name;
    }
}