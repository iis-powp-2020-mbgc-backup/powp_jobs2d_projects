package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;

public class ComprisingCommandsCounterVisitor implements CommandVisitorInterface
{
    private Job2dDriver job2dDriver;
    private int operateToCounter = 0;
    private int setPositionCounter = 0;

    public int getOperateToCounter()
    {
        return operateToCounter;
    }

    public int getSetPositionCounter()
    {
        return setPositionCounter;
    }

    public ComprisingCommandsCounterVisitor(Job2dDriver driver)
    {
        this.job2dDriver = driver;
    }

    @Override
    public void visit(OperateToCommand driver)
    {
        operateToCounter++;
    }

    @Override
    public void visit(SetPositionCommand driver)
    {
        setPositionCounter++;
    }

    @Override
    public void visit(ICompoundCommand driver)
    {
        Iterator<DriverCommand> commands = driver.iterator();
        while(commands.hasNext()) {
            commands.next().accept(this);
        }
    }

    public int getAllCommandsCounter()
    {
        return operateToCounter + setPositionCounter;
    }
}
