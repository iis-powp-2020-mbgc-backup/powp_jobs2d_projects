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

			if (driverCommand instanceof SetPositionCommand) {
				jsonDriverCommand.operations = getOperationFromDriverCommand((SetPositionCommand) driverCommand);
			} else if (driverCommand instanceof OperateToCommand) {
				jsonDriverCommand.operations = getOperationFromDriverCommand((OperateToCommand) driverCommand);
			} else if (driverCommand instanceof CompoundCommand) {
				jsonDriverCommand.operations = getOperationFromDriverCommand((CompoundCommand) driverCommand);
			}

			gson.toJson(jsonDriverCommand, JsonDriverCommand.class, writer);
			writer.close();
			return jsonFile;
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	private List<JsonDriverCommand.operation> getOperationFromDriverCommand(SetPositionCommand driverCommand) {
		List<JsonDriverCommand.operation> operationList = new ArrayList<>();
		JsonDriverCommand.operation op = new JsonDriverCommand.operation();
		op.opName = "SetPositionCommand";
		op.x = driverCommand.getPosX();
		op.y = driverCommand.getPosY();
		operationList.add(op);
		return operationList;
	}

	private List<JsonDriverCommand.operation> getOperationFromDriverCommand(OperateToCommand driverCommand) {
		List<JsonDriverCommand.operation> operationList = new ArrayList<>();
		JsonDriverCommand.operation op = new JsonDriverCommand.operation();
		op.opName = "OperateToCommand";
		op.x = driverCommand.getPosX();
		op.y = driverCommand.getPosY();
		operationList.add(op);
		return operationList;
	}

	private List<JsonDriverCommand.operation> getOperationFromDriverCommand(CompoundCommand driverCommand) {
		List<JsonDriverCommand.operation> operationList = new ArrayList<>();
		for (DriverCommand command : driverCommand) {
			if (command instanceof SetPositionCommand) {
				operationList.addAll(getOperationFromDriverCommand((SetPositionCommand) command));
			} else if (command instanceof OperateToCommand) {
				operationList.addAll(getOperationFromDriverCommand((OperateToCommand) command));
			}

		}
		return operationList;
	}
}
