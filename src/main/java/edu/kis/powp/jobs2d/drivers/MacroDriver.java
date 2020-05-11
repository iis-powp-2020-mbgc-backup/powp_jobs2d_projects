package edu.kis.powp.jobs2d.drivers;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import edu.kis.powp.jobs2d.features.DriverFeature;


/**
 * Driver command Manager.
 */
public class MacroDriver implements Job2dDriver {

    List<DriverCommand> commands = new ArrayList<DriverCommand>();
    private Job2dDriver jd;
    public void clearMacro(){
        commands.clear();
        this.reverseDriver();
    }
    public void setLineDriver(Job2dDriver jd){
        this.jd=jd;
    }
    public void reverseDriver(){
        DriverFeature.getDriverManager().setCurrentDriver(jd);
    }
    @Override
    public void setPosition(int i, int i1) {
        commands.add(new SetPositionCommand(i, i1));
    }

    @Override
    public void operateTo(int i, int i1) {
        commands.add(new OperateToCommand(i, i1));
    }

    public List<DriverCommand> getCommands() {
        return commands;
    }

    @Override
    public String toString () {
        return "MacroDriver";
    }

}
