package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.observer.Publisher;

import java.util.logging.Logger;

public class InkUsageDriverDecorator implements Job2dDriver {
    private int x0, y0;
    private double inkLimit;
    private double totalUsage;
    private Logger logger = Logger.getLogger("global");
    private Job2dDriver driver;
    private Publisher pub;
    private boolean isUnAvailable = false;
    private double maxInkLimit;

    public InkUsageDriverDecorator(Job2dDriver driver, double maxInkLimit) {
        super();
        this.driver = driver;
        this.x0 = 0;
        this.y0 = 0;
        this.totalUsage = 0;
        this.inkLimit = maxInkLimit;
        this.maxInkLimit = this.inkLimit;
        pub = new Publisher();
        pub.addSubscriber(new NoInkObserver(this));
    }

    public void setDriver(Job2dDriver driver)
    {
        this.driver = driver;
        pub = new Publisher();
        pub.addSubscriber(new NoInkObserver(this));
    }

    @Override
    public void setPosition(int x, int y) {
        double wasted = inkCounter(this.x0, x, this.y0, y);
        totalUsage += wasted;
        driver.setPosition(x, y);
        this.x0 = x;
        this.y0 = y;
    }

    private double inkCounter(int xStart, int xEnd, int yStart, int yEnd){
        double count = 0.0;
        count = Math.sqrt(Math.pow(xStart - xEnd, 2.0) + Math.pow(yStart - yEnd, 2.0));
        return count;
    }

    public void setIsAvailable(){
        this.isUnAvailable = false;
    }

    @Override
    public void operateTo(int x1, int y1) {

        double wasted = inkCounter(this.x0, x1, this.y0, y1);

        if((inkLimit-wasted) < 0) {
            this.logger.info("You don't have enough ink to do this!");
            if(!isUnAvailable){
                pub.notifyObservers();
                isUnAvailable = true;
            }
        }
        else
        {
            driver.operateTo(x1,y1);
            this.x0 = x1;
            this.y0 = y1;
            inkLimit -= wasted;
            totalUsage += wasted;
            this.logger.info("Ink wasted per move: " + String.format ("%.3f", wasted) + "units");
            this.logger.info("Total used ink: " + String.format ("%.3f", totalUsage) + "units");
            this.logger.info("Remaining ink: " + String.format ("%.3f", inkLimit) + "units");
            this.logger.info("-----------------------------------");
        }
    }

    public double getInkLimit()
    {
        return this.inkLimit;
    }

    public double getTotalUsage()
    {
        return this.totalUsage;
    }

    public double getMaxInkLimit()
    {
        return this.maxInkLimit;
    }

    public void setInkLimit(double inkLimit)
    {
        this.inkLimit = inkLimit;
    }

}
