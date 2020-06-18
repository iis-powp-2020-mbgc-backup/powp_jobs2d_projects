package edu.kis.powp.jobs2d.features;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

public class CommandFactory implements Subscriber {
    public Map<String, DriverCommand> commandsMap = new HashMap<>();
    private DriverCommandManager driverCommandManager;
    private CommandManagerWindow window;

    private static CommandFactory commandFactory = null;

    public static CommandFactory getInstance() {
        if (commandFactory == null) {
            commandFactory = new CommandFactory();
        }
        return commandFactory;
    }

    private CommandFactory() {
        driverCommandManager = CommandsFeature.getDriverCommandManager();
        driverCommandManager.getChangePublisher().addSubscriber(this);
    }


    public DriverCommand getCommand(String commandName) throws CloneNotSupportedException {
        DriverCommand resultCommand=null;
        if(commandsMap.keySet().contains(commandName)){
            resultCommand = commandsMap.get(commandName).clone();
        }else{
            throw new IllegalArgumentException("Name not found");
        }

        if (resultCommand instanceof CompoundCommand) {
            ((CompoundCommand) resultCommand).setName(commandName);
        }
        return resultCommand;
    }

    public void setCommand(DriverCommand command) {
        commandsMap.put(command.toString(), command);
    }

    public void removeCommand(String commandName) {
        if(commandsMap.keySet().contains(commandName)){
            commandsMap.remove(commandName);
        }else{
            throw new IllegalArgumentException("Name not found");
        }
    }

    public void runCommand(String commandName) throws CloneNotSupportedException {
        if(commandsMap.keySet().contains(commandName)){
            driverCommandManager.setCurrentCommand(getCommand(commandName));
            driverCommandManager.runCurrentCommand();
        }else{
            throw new IllegalArgumentException("Name not found");
        }
    }


    public Set<String> getCommandNames() {
        return commandsMap.keySet();
    }

    public void addObserver(CommandManagerWindow window){
        this.window = window;
    }

    @Override
    public void update() {
        DriverCommand command = driverCommandManager.getCurrentCommand();
        String commandName = driverCommandManager.getCurrentCommandString().replace("CompoundCommand name: ", "");
        commandsMap.put(commandName, command);
        window.addCommandToFactoryList(commandName);
    }
}