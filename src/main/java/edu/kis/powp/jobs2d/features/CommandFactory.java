package edu.kis.powp.jobs2d.features;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

public class CommandFactory {
    private Map<String, DriverCommand> commandsMap = new HashMap<>();
    private static CommandFactory commandFactory = null;

    public static CommandFactory getInstance() {
        if (commandFactory == null) {
            commandFactory = new CommandFactory();
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
    }

    public void removeCommand(String commandName) {
        if (commandsMap.keySet().contains(commandName)) {
            commandsMap.remove(commandName);
        } else {
            throw new IllegalArgumentException("Name " + commandName + " not found");
        }
    }

    public Set<String> getCommandNames() {
        return commandsMap.keySet();
    }
}