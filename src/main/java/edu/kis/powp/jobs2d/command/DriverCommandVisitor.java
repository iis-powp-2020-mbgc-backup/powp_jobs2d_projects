package edu.kis.powp.jobs2d.command;

/**
 * Basic interface for visitor pattern.
 */
public interface DriverCommandVisitor {
	void visit(ICompoundCommand driverCommand);
	void visit(OperateToCommand driverCommand);
	void visit(SetPositionCommand driverCommand);
}
