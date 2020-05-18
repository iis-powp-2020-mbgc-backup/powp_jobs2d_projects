package edu.kis.powp.jobs2d.drivers;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;




public class MacroDriver implements Job2dDriver {

    List<DriverCommand> commands = new ArrayList<DriverCommand>();
    private Job2dDriver jd;
    public void clearMacro(){
        commands.clear();
    }
    public void setPreviousDriver(Job2dDriver jd){
        this.jd=jd;
    }
    public Job2dDriver getPreviousDriver(){
        return  jd;
    }

    @Override
    public void setPosition(int i, int i1) {
        commands.add(new SetPositionCommand(i, i1));
    }

    @Override
    public void operateTo(int x, int y) {
        commands.add(new OperateToCommand(x, y));
    }

    public List<DriverCommand> getCommands() {
        return commands;
    }

    @Override
    public String toString () {
        return "MacroDriver";
    }

}
