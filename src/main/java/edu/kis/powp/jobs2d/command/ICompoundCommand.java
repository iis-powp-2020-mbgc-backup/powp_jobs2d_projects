package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Interface extending Job2dDriverCommand to execute more than one command.
 */
public interface ICompoundCommand extends DriverCommand, Iterable<DriverCommand> {

    Iterator<DriverCommand> iterator();

    @Override
    default DriverCommand clone(){
        List<DriverCommand> commands = new ArrayList<>();
        for (DriverCommand command : this) {
            command.clone();
        }
        return new DefaultCompoundCommand(commands);
    }

}
