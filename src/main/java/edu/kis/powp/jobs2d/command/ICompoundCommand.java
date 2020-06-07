package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.visitor.CommandVisitorInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Interface extending Job2dDriverCommand to execute more than one command.
 */
public interface ICompoundCommand extends DriverCommand, Iterable<DriverCommand> {

    Iterator<DriverCommand> iterator();

    @Override
    default DriverCommand clone() throws CloneNotSupportedException{
        List<DriverCommand> commands = new ArrayList<>();
        for (DriverCommand command : this) {
            commands.add(command.clone());
        }
        return new DefaultCompoundCommand(commands);
    }

    default public void accept(CommandVisitorInterface visitor) {
        visitor.visit(this);
    }
}
