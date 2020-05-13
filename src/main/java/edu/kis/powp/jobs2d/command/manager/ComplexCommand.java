package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.ArrayList;

public class ComplexCommand implements DriverCommand {

    private ArrayList<DriverCommand> commandList;

    public ComplexCommand(ArrayList<DriverCommand> commands) {
        this.commandList = commands;
    }

    @Override
    public void execute(Job2dDriver driver) {
        for(int i=0;i<commandList.size();i++) {
            commandList.get(i).execute(driver);
        }
    }
}
