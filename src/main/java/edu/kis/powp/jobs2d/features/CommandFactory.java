package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.DefaultCompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import java.util.*;

public class CommandFactory {

    private Map<String, DriverCommand> shapes;

    public CommandFactory() {
        shapes = new HashMap<>();
    }

    public DriverCommand get(String name){
        try {
            return shapes.get(name).clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void add(DefaultCompoundCommand complexCommand) throws IllegalArgumentException{
        try{
            shapes.put(complexCommand.toString(), complexCommand.clone());
        }catch(CloneNotSupportedException e){
            throw new IllegalArgumentException();
        }
    }
    public void add(DriverCommand complexCommand, String name) throws IllegalArgumentException{
        try{
            shapes.put(name, complexCommand.clone());
        }catch(CloneNotSupportedException e){
            throw new IllegalArgumentException();
        }
    }

    public Set<String> getNamesOfStored(){
        return shapes.keySet();
    }

    public void remove(String name){
        shapes.remove(name);
    }

    public void clear(){
        shapes.clear();
    }

    public void setCommandManagerPublisher(DriverCommandManager commandManager){
        commandManager.getChangePublisher().addSubscriber(() -> {
            DriverCommand currentCommand = commandManager.getCurrentCommand();
            if(currentCommand instanceof DefaultCompoundCommand){
                this.add((DefaultCompoundCommand)currentCommand);
            }else{
                this.add(currentCommand, commandManager.getCurrentCommandString());
            }
        });

    }


}
