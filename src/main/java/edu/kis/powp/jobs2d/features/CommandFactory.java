package edu.kis.powp.jobs2d.features;

import java.util.HashMap;
import java.util.Map;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

public class CommandFactory {
    private Map<String, DriverCommand> commandsMap;

    public CommandFactory() {
        this.commandsMap = new HashMap<>();
    }

    public DriverCommand getCommand(String name) {
        try {
            return this.commandsMap.get(name).clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void addCommand(CompoundCommand command) throws CloneNotSupportedException {
        commandsMap.put(command.toString(), command.clone());
    }

    public void addCommand(DriverCommand command, String name) throws CloneNotSupportedException {
        commandsMap.put(name, command.clone());
    }

    public void removeCommand(String name) {
        commandsMap.remove(name);
    }

    public void setCommandManagerPublisher(DriverCommandManager driverCommandManager) {
        driverCommandManager.getChangePublisher().addSubscriber(() -> {
            DriverCommand driverCommand = driverCommandManager.getCurrentCommand();
            try {
                if (driverCommand instanceof CompoundCommand) {
                    this.addCommand((CompoundCommand) driverCommand);
                } else {
                    this.addCommand(driverCommand, driverCommandManager.getCurrentCommandString());

                }
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException();
            }
        });
    }
}