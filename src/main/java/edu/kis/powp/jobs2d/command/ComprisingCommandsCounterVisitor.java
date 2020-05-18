package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComprisingCommandsCounterVisitor implements CommandVisitorInterface
{
    Job2dDriver job2dDriver;
    public int operateToCounter = 0;
    public int setPositionCounter = 0;
    public int iCompoundCounter = 0;

    public ComprisingCommandsCounterVisitor(Job2dDriver driver)
    {
        this.job2dDriver = driver;
    }

    @Override
    public void visit(OperateToCommand driver)
    {
        operateToCounter++;
        driver.execute(this.job2dDriver);
    }

    @Override
    public void visit(SetPositionCommand driver)
    {
        setPositionCounter++;
        driver.execute(this.job2dDriver);
    }

    @Override
    public void visit(ICompoundCommand driver)
    {
        driver.execute(this.job2dDriver);
    }

    public int getAllCommandsCounter()
    {
        return operateToCounter + setPositionCounter;
    }
}
