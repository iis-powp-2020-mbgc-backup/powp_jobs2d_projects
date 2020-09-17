package edu.kis.powp.jobs2d.features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.parsing.JsonParser;

public class CommandFactory {
    private Map<String, DriverCommand> commandsMap = new HashMap<>();
    private static CommandFactory commandFactory = null;

    public static CommandFactory getInstance() {
        if (commandFactory == null) {
            commandFactory = new CommandFactory();
            loadFromFile();
        }
        return commandFactory;
    }

    public DriverCommand getCommand(String commandName) {
        DriverCommand resultCommand = null;
        if (commandsMap.keySet().contains(commandName)) {
            try {
                resultCommand = commandsMap.get(commandName).clone();
            } catch (CloneNotSupportedException e) {
                throw new IllegalStateException("this should never happen: command has to be clonable since its cloned in addCommand", e);
            }
        } else {
            throw new IllegalArgumentException("Name " + commandName + " not found");
        }
        return resultCommand;
    }

    public void addCommand(DriverCommand command) throws CloneNotSupportedException {
        commandsMap.put(command.toString().replace("CompoundCommand name: ", ""), command.clone());
        saveToFile();
    }

    public void removeCommand(String commandName) {
        if (commandsMap.keySet().contains(commandName)) {
            commandsMap.remove(commandName);
            saveToFile();
        } else {
            throw new IllegalArgumentException("Name " + commandName + " not found");
        }
    }

    public Set<String> getCommandNames() {
        return commandsMap.keySet();
    }

    private void saveToFile() {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("command_factory")))) {
            for (String item : commandsMap.keySet()) {
                bufferedWriter.write("{\"" + item + "\":" + (new JsonParser()).parseToString(commandsMap.get(item)) + "}" + "\n");
            }
		} catch (IOException exception) {
			exception.printStackTrace();
		}
    }

    private static void loadFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("command_factory"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(":", 2).length == 2) {
                    DriverCommand command = (new JsonParser()).parseFromString(line.split(":", 2)[1].replaceAll("}$", ""));
                    commandFactory.commandsMap.put(line.split(":", 2)[0].replace("{", "").replaceAll("\"", ""), command);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}