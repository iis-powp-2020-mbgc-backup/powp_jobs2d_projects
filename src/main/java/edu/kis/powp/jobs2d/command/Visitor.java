package edu.kis.powp.jobs2d.command;

public interface Visitor {
        public void visit(
            OperateToCommand driver);
        public void visit(
            SetPositionCommand driver);
        public void visit(
            ICompoundCommand driver);
}
