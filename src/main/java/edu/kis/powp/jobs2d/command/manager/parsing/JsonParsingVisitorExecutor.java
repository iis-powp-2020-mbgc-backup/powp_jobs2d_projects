package edu.kis.powp.jobs2d.command.manager.parsing;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.List;

public class JsonParsingVisitorExecutor implements CommandVisitorInterface {
	private List<JsonParser.JsonDriverCommand.operation> result = new ArrayList<>();

	public List<JsonParser.JsonDriverCommand.operation> getResult() {
		return result;
	}

	@Override
	public void visit(OperateToCommand driver) {
		JsonParser.JsonDriverCommand.operation op = new JsonParser.JsonDriverCommand.operation();
		op.opName = "OperateToCommand";
		op.x = driver.getPosX();
		op.y = driver.getPosY();
		result.add(op);
	}

	@Override
	public void visit(SetPositionCommand driver) {
		JsonParser.JsonDriverCommand.operation op = new JsonParser.JsonDriverCommand.operation();
		op.opName = "SetPositionCommand";
		op.x = driver.getPosX();
		op.y = driver.getPosY();
		result.add(op);
	}

	@Override
	public void visit(ICompoundCommand driver) {
		for (DriverCommand driverCommand : driver) driverCommand.accept(this);
	}
}
