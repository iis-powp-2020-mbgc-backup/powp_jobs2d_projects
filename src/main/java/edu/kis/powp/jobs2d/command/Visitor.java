package edu.kis.powp.jobs2d.command;

/**
 * Basic interface for visitor pattern.
 */
public interface Visitor {
    void visit(DriverCommand driverCommand);
    void visit(ICompoundCommand compoundCommand);
}
