package edu.kis.powp.jobs2d.command.manager.parsing;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
	String pathToFile;
	String commandName = "No command has been set yet";

	private class JsonDriverCommand {
		String commandName;

		class operation {
			String opName;
			int x;
			int y;
		}

		List<operation> operations;
	}

	public JsonParser(String pathToFile) {
		this.pathToFile = pathToFile;
	}

	public List<DriverCommand> parseFromImport() {
		try {
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(pathToFile));
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

	public String getCommandName() {
		return commandName;
	}

	public List<DriverCommand> parseToExport() {
		// TODO: 02.06.2020 to be done
		return null;
	}
}
