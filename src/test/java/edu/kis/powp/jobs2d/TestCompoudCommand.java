package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Test class for ICompoundCommand interface.
 */
public class TestCompoudCommand implements ICompoundCommand, Visitable {
    private List<DriverCommand> commands = Arrays.asList(new SetPositionCommand(0,0), new SetPositionCommand(0,0), new SetPositionCommand(0,0));

    @Override
    public Iterator<DriverCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public void execute(Job2dDriver driver) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
