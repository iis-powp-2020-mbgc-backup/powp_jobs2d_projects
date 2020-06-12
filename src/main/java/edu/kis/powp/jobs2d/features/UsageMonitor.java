package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;
import java.util.logging.Logger;

public class UsageMonitor implements CommandVisitorInterface {

    private Job2dDriver job2dDriver;
    private int operateToDistance;
    private int positionDistance;
    private int currentX;
    private int currentY;

    private Logger logger = Logger.getLogger("global");

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

        logger.info("Operate distance: " + operateToDistance + "\nHead distance: " + positionDistance);
    }

    @Override
    public void visit(SetPositionCommand driver)
    {
        positionDistance += calculateDistance(driver.getPosX(), driver.getPosY());
        currentX = driver.getPosX();
        currentY = driver.getPosY();

        logger.info("Operate distance: " + operateToDistance + "\nHead distance: " + positionDistance);
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
