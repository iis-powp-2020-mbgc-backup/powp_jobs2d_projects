package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.drivers.DriverComposite;

public interface Visitor {
	void visit(OperateToCommand driver);

	void visit(SetPositionCommand driver);

	void visit(ICompoundCommand driver);

	void visit(DriverComposite driver);
}
