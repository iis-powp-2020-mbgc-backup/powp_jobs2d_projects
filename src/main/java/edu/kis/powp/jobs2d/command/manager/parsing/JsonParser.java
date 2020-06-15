package edu.kis.powp.jobs2d.command.manager.parsing;

import com.google.gson.Gson;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;
/**Class responsible for creating
 * command
 * from String in JSON format
 *  and exporting it to .json file*/
public class JsonParser implements Parser {
	/**Inner class needed to parse JSON */
	private static class JsonDriverCommand {
		/**Operation structure*/
		static class operation {
			/**name of operation*/
			String opName;
			/**X coordinate*/
			int x;
			/**Y coordinate*/
			int y;
		}
		/**List of operations*/
		List<operation> operations;
	}

	/**Creating command from String
	 * @param commandAsString command in String
	 * @return command object*/
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
	/**Creating JSON String from command object
	 * @param driverCommand command to parse
	 * @return command in JSON format*/
	@Override
	public String parseToString(DriverCommand driverCommand) {
		Gson gson = new Gson();
		JsonDriverCommand jsonDriverCommand = new JsonDriverCommand();

		jsonDriverCommand.operations = getOperationFromDriverCommand(driverCommand);
		return gson.toJson(jsonDriverCommand, JsonDriverCommand.class);
	}
	/**Creating a list of commands
	 * @param driverCommand command
	 * @return list of operations*/
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
