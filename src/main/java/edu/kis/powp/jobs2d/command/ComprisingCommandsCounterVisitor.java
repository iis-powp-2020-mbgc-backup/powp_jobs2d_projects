package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComprisingCommandsCounterVisitor implements CommandVisitorInterface
{
    private Job2dDriver job2dDriver;
    private int operateToCounter = 0;
    private int setPositionCounter = 0;
    private int CompoundCounter = 0;

    public int getOperateToCounter()
    {
        return operateToCounter;
    }

    public int getSetPositionCounter()
    {
        return setPositionCounter;
    }

    public int getCompoundCounter()
    {
        return CompoundCounter;
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
        driver.iterator().forEachRemaining(c -> c.accept(this));
    }

    public int getAllCommandsCounter()
    {
        return operateToCounter + setPositionCounter + CompoundCounter;
    }
}
