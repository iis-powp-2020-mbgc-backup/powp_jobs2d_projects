package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

public class UsageMonitor extends Job2dDecorator {

    private int operateToDistance;
    private int positionDistance;
    private int currentX;
    private int currentY;

    private Logger logger = Logger.getLogger("global");

    public UsageMonitor(Job2dDriver job2dDriver) {
        super(job2dDriver);
    }

    @Override
    public void setPosition(int x, int y) {
        super.operateTo(x, y);

        positionDistance += calculateDistance(x, y);
        currentX = x;
        currentY = y;

        logger.info("Operate distance: " + operateToDistance + "\nHead distance: " + positionDistance);
    }

    private double calculateDistance(int x, int y) {
        return Math.sqrt(Math.pow(x - currentX, 2) + Math.pow(y - currentY, 2));
    }

    @Override
    public void operateTo(int x, int y) {
        super.operateTo(x, y);

        operateToDistance += calculateDistance(x, y);
        currentX = x;
        currentY = y;

        logger.info("Operate distance: " + operateToDistance + "\nHead distance: " + positionDistance);
    }

    @Override
    public String toString() {
        return job2dDriver.toString();
    }
}
