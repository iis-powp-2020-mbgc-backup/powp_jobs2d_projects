package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandFactory {
    private Map<String, DriverCommand> commands = new HashMap<>();
    private static CommandFactory commandFactory;
    public static CommandFactory getInstance() {
        commandFactory = new CommandFactory();
        return commandFactory;
    }

    public void addCommand(DriverCommand command) throws CloneNotSupportedException {
        commands.put(command.toString(), command.clone());
    }

    public void removeCommand(String name) {
        commands.remove(name);
    }

    public DriverCommand getCommand(String name) throws CloneNotSupportedException {
        return commands.get(name).clone();
    }

    public Set<String> getCommandNames() {
        return commands.keySet();
    }

}



