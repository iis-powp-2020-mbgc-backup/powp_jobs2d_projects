package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.DefaultCompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.ImmutableCompoundCommand;

import java.util.*;

public class CommandFactory {

    private Map<String, DriverCommand> shapes;

    public CommandFactory() {
        shapes = new HashMap<>();
    }

    public DriverCommand get(String name){
        return shapes.get(name);
    }

    public void add(DefaultCompoundCommand complexCommand) throws IllegalArgumentException{
        try{
            shapes.put(complexCommand.toString(), complexCommand.clone());
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

}
