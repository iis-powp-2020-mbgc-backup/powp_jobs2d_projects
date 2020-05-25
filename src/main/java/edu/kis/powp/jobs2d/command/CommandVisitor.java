package edu.kis.powp.jobs2d.command;

/**
 * Basic interface for visitor pattern.
 */
public interface CommandVisitor {

    void visit(ICompoundCommand driverCommand);

    void visit(OperateToCommand driverCommand);

    void visit(SetPositionCommand driverCommand);
}