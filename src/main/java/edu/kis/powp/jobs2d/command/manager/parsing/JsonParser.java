package edu.kis.powp.jobs2d.command.manager.parsing;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonParser implements Parser {
	File jsonFile;
	String commandName = "No command has been set yet";

	private static class JsonDriverCommand {
		String commandName;

		static class operation {
			String opName;
			int x;
			int y;
		}

		List<operation> operations;
	}

	public JsonParser(File jsonFile) {
		this.jsonFile = jsonFile;
	}

	@Override
	public List<DriverCommand> parseFromImport() {
		try {
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(jsonFile));
			JsonDriverCommand jsonDriverCommand = gson.fromJson(reader, JsonDriverCommand.class);
			List<DriverCommand> driverCommands = new ArrayList<>();
			for (JsonDriverCommand.operation op : jsonDriverCommand.operations)
				if (op.opName.equals("SetPositionCommand")) driverCommands.add(new SetPositionCommand(op.x, op.y));
				else if (op.opName.equals("OperateToCommand")) driverCommands.add(new OperateToCommand(op.x, op.y));
			commandName = jsonDriverCommand.commandName;
			return driverCommands;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getCommandName() {
		return commandName;
	}

	@Override
	public File parseToExport(DriverCommand driverCommand) {
		try {
			Gson gson = new Gson();
			JsonWriter writer = new JsonWriter(new FileWriter(jsonFile));
			JsonDriverCommand jsonDriverCommand = new JsonDriverCommand();
			jsonDriverCommand.commandName = driverCommand.toString();

			jsonDriverCommand.operations = getOperationFromDriverCommand(driverCommand);

			gson.toJson(jsonDriverCommand, JsonDriverCommand.class, writer);
			writer.close();
			return jsonFile;
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return null;
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
