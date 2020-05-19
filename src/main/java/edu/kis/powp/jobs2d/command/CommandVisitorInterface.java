package edu.kis.powp.jobs2d.command;

public interface CommandVisitorInterface
{
	void visit(OperateToCommand driver);

	void visit(SetPositionCommand driver);

	void visit(ICompoundCommand driver);
}
