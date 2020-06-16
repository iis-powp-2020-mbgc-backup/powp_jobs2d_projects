package edu.kis.powp.jobs2d.command.manager.parsing;

import com.google.gson.Gson;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating
 * command
 * from String in JSON format
 * and exporting it to .json file
 */
public class JsonParser implements Parser {
	/**
	 * Inner class needed to parse JSON
	 */
	public static class JsonDriverCommand {
		/**
		 * Operation structure
		 */
		static class operation {
			/**
			 * name of operation
			 */
			String opName;
			/**
			 * X coordinate
			 */
			int x;
			/**
			 * Y coordinate
			 */
			int y;
		}

		/**
		 * List of operations
		 */
		List<operation> operations;
	}

	/**
	 * Creating command from String
	 *
	 * @param commandAsString command in String
	 * @return command object
	 */
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

	/**
	 * Creating JSON String from command object
	 *
	 * @param driverCommand command to parse
	 * @return command in JSON format
	 */
	@Override
	public String parseToString(DriverCommand driverCommand) {
		Gson gson = new Gson();
		JsonDriverCommand jsonDriverCommand = new JsonDriverCommand();
		JsonParsingVisitorExecutor visitor = new JsonParsingVisitorExecutor();
		driverCommand.accept(visitor);
		jsonDriverCommand.operations = visitor.getResult();
		return gson.toJson(jsonDriverCommand, JsonDriverCommand.class);
	}

}
