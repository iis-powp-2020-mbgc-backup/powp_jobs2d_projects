package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;
import java.util.logging.Logger;

public class UsageMonitor implements CommandVisitorInterface {

    private Job2dDriver job2dDriver;
    private int operateToDistance = 0;
    private int positionDistance = 0;
    private int currentX = 0;
    private int currentY = 0;

    public int getOperateToDistance() {
        return operateToDistance;
    }

    public int getPositionDistance() {
        return positionDistance;
    }

    public UsageMonitor(Job2dDriver driver)
    {
        this.job2dDriver = driver;
    }

    @Override
    public void visit(OperateToCommand driver)
    {
        operateToDistance += calculateDistance(driver.getPosX(), driver.getPosY());
        currentX = driver.getPosX();
        currentY = driver.getPosY();
    }

    @Override
    public void visit(SetPositionCommand driver)
    {
        positionDistance += calculateDistance(driver.getPosX(), driver.getPosY());
        currentX = driver.getPosX();
        currentY = driver.getPosY();
    }

    @Override
    public void visit(ICompoundCommand driver)
    {
        Iterator<DriverCommand> commands = driver.iterator();
        while(commands.hasNext()) {
            commands.next().accept(this);
        }
    }

    private double calculateDistance(int x, int y) {
        return Math.sqrt(Math.pow(x - currentX, 2) + Math.pow(y - currentY, 2));
    }
}
