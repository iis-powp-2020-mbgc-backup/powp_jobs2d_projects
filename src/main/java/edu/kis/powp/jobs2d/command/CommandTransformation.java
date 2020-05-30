package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandTransformation implements CommandVisitorInterface {
    List<DriverCommand> commands;

    public CommandTransformation(){
        commands = new ArrayList<>();
    }

    public ICompoundCommand getTransformedCommand() {
        return new ICompoundCommand() {
            @Override
            public Iterator<DriverCommand> iterator() {
                return commands.iterator();
            }

            @Override
            public void execute(Job2dDriver driver) {
                commands.forEach(c -> c.execute(driver));
            }
        };
    }

    @Override
    public void visit(OperateToCommand driver) {
        DriverCommand transformedCommand = new OperateToCommand(getNewX(driver.getPosX(), driver.getPosY()), getNewY(driver.getPosX(), driver.getPosY()));
        commands.add(transformedCommand);
    }

    @Override
    public void visit(SetPositionCommand driver) {
        SetPositionCommand transformedCommand = new SetPositionCommand(getNewX(driver.getPosX(), driver.getPosY()), getNewY(driver.getPosX(), driver.getPosY()));
        commands.add(transformedCommand);
    }

    @Override
    public void visit(ICompoundCommand driver) {
        for (Iterator<DriverCommand> i = driver.iterator(); i.hasNext(); ) {
            i.next().accept(this);
        }
    }

    int getNewX(int x, int y){
        return x;
    }

    int getNewY(int x, int y){
        return y;
    }
}
