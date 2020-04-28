package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

public interface Visitor {
    void visit(DriverCommand driverCommand);
    void visit(ICompoundCommand compoundCommand);
}
