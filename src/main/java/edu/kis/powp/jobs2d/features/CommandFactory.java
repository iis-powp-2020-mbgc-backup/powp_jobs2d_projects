package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.DefaultCompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.ImmutableCompoundCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CommandFactory {

    private HashMap<String, ICompoundCommand> shapes;

    public CommandFactory() {
        shapes = new HashMap<>();
    }

    public ICompoundCommand get(String name){
        return shapes.get(name);
    }

    public void add(String name, ICompoundCommand commands) throws IllegalArgumentException{
        try{
            shapes.put(name, (DefaultCompoundCommand) commands.clone());
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
