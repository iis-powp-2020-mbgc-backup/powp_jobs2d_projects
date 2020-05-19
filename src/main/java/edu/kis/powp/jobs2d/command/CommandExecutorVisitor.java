package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;

public class CommandExecutorVisitor implements CommandVisitorInterface
{
    private Job2dDriver job2dDriver;

    public CommandExecutorVisitor(Job2dDriver driver)
    {
        this.job2dDriver = driver;
    }

    @Override
    public void visit(OperateToCommand driver)
    {
        driver.execute(this.job2dDriver);
    }

    @Override
    public void visit(SetPositionCommand driver)
    {
        driver.execute(this.job2dDriver);
    }

    @Override
    public void visit(ICompoundCommand driver)
    {
        Iterator<DriverCommand> commands = driver.iterator();
        while(commands.hasNext()) {
            commands.next().accept(this);
        }
    }
}
