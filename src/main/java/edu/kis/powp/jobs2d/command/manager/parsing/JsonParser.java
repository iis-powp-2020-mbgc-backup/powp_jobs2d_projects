package edu.kis.powp.jobs2d.command.manager.parsing;

import com.google.gson.Gson;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class JsonParser implements Parser {

	private static class JsonDriverCommand {
		static class operation {
			String opName;
			int x;
			int y;
		}

		List<operation> operations;
	}


	@Override
	public DriverCommand parseFromString(String commandAsString) {
		Gson gson = new Gson();

		JsonDriverCommand jsonDriverCommand = gson.fromJson(commandAsString, JsonDriverCommand.class);
		List<DriverCommand> driverCommands = new ArrayList<>();
		for (JsonDriverCommand.operation op : jsonDriverCommand.operations)
			if (op.opName.equals("SetPositionCommand")) driverCommands.add(new SetPositionCommand(op.x, op.y));
			else if (op.opName.equals("OperateToCommand")) driverCommands.add(new OperateToCommand(op.x, op.y));

		if (driverCommands.size() == 1)
			return driverCommands.get(0);
		return new CompoundCommand(driverCommands);
	}

	@Override
	public String parseToString(DriverCommand driverCommand) {
		Gson gson = new Gson();
		JsonDriverCommand jsonDriverCommand = new JsonDriverCommand();

		jsonDriverCommand.operations = getOperationFromDriverCommand(driverCommand);
		return gson.toJson(jsonDriverCommand, JsonDriverCommand.class);
	}

	private List<JsonDriverCommand.operation> getOperationFromDriverCommand(DriverCommand driverCommand) {
		List<JsonDriverCommand.operation> operationList = new ArrayList<>();
		if (driverCommand instanceof OperateToCommand) {
			JsonDriverCommand.operation op = new JsonDriverCommand.operation();
			OperateToCommand command = (OperateToCommand) driverCommand;
			op.opName = "OperateToCommand";
			op.x = command.getPosX();
			op.y = command.getPosY();
			operationList.add(op);
		} else if (driverCommand instanceof SetPositionCommand) {
			JsonDriverCommand.operation op = new JsonDriverCommand.operation();
			SetPositionCommand command = (SetPositionCommand) driverCommand;
			op.opName = "SetPositionCommand";
			op.x = command.getPosX();
			op.y = command.getPosY();
			operationList.add(op);
		} else if (driverCommand instanceof CompoundCommand) {
			CompoundCommand commands = (CompoundCommand) driverCommand;
			for (DriverCommand command : commands) {
				if (command instanceof SetPositionCommand) {
					operationList.addAll(getOperationFromDriverCommand(command));
				} else if (command instanceof OperateToCommand) {
					operationList.addAll(getOperationFromDriverCommand(command));
				}
			}
		}

		return operationList;
	}

}
