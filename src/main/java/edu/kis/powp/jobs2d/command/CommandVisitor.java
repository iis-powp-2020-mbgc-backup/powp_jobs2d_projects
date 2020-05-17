package edu.kis.powp.jobs2d.command;

public interface CommandVisitor
{
    void visit(DriverCommand driver);

    void visit(OperateToCommand driver);

    void visit(SetPositionCommand driver);

    void visit(ICompoundCommand driver);
}
