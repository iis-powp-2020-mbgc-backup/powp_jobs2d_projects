package edu.kis.powp.jobs2d.drivers;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

public class InkUsageDriver implements Job2dDriver {

    private int x0, y0;
    private double inkLimit;
    private double totalUsage;
    Logger logger = Logger.getLogger("global");

    private Job2dDriver driver;

    public InkUsageDriver(Job2dDriver driver, double inkLimit) {
        super();
        this.driver = driver;
        this.x0 = 0;
        this.y0 = 0;
        this.totalUsage = 0;
        this.inkLimit = inkLimit;
    }

    @Override
    public void setPosition(int x, int y){
        driver.setPosition(x,y);
        this.x0 = x;
        this.y0 = y;
    }

    public void restoreInk(double amount)
    {
        this.inkLimit += amount;
    }

    double inkCounter(int xStart, int xEnd, int yStart, int yEnd){
        double count = 0.0;
        count = Math.sqrt(Math.pow(xStart - xEnd, 2.0) + Math.pow(yStart - yEnd, 2.0));
        return count;
    }

    @Override
    public void operateTo(int x1, int y1) {

        double wasted;
        wasted = inkCounter(this.x0, x1, this.y0, y1);
        driver.operateTo(x1,y1);
        this.x0 = x1;
        this.y0 = y1;


        inkLimit -= wasted;

        if(inkLimit <= 0)
            this.logger.info("You don't have enough ink to do this!");
        else
        {
            this.logger.info("Ink wasted per move: " + String.format ("%.3f", wasted) + "units");
            this.logger.info("Total used ink: " + String.format ("%.3f", totalUsage) + "units");
            this.logger.info("-----------------------------------");
        }
        totalUsage += wasted;

    }
}
