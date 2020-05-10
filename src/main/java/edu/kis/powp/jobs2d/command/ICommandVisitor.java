package edu.kis.powp.jobs2d.command;

public interface ICommandVisitor {

    void visit(SetPositionCommand setPositionCommand);

    void visit(OperateToCommand operateToCommand);

    void visit(ICompoundCommand compoundCommand);
}
