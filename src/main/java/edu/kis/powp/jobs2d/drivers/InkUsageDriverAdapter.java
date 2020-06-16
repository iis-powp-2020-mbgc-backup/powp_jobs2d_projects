package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.observer.Publisher;

import java.util.logging.Logger;

public class InkUsageDriverAdapter implements Job2dDriver {

    private int x0, y0;
    private double inkLimit;
    private double totalUsage;
    private Logger logger = Logger.getLogger("global");
    private Job2dDriver driver;
    private Publisher pub = null;
    private boolean bool = false;
    public InkUsageDriverAdapter(Job2dDriver driver, double inkLimit) {
        super();
        this.driver = driver;
        this.x0 = 0;
        this.y0 = 0;
        this.totalUsage = 0;
        this.inkLimit = inkLimit;
        pub = new Publisher();
        pub.addSubscriber(new InkUsageObserver(this));
    }

    public InkUsageDriverAdapter(Job2dDriver driver, double inkLimit, double totalUsage) {
        super();
        this.driver = driver;
        this.x0 = 0;
        this.y0 = 0;
        this.totalUsage = totalUsage;
        this.inkLimit = inkLimit;
        pub = new Publisher();
        pub.addSubscriber(new InkUsageObserver(this));
    }

    @Override
    public void setPosition(int x, int y) {
        double wasted = inkCounter(this.x0, x, this.y0, y);
        if((inkLimit-wasted) >= 0){
            driver.setPosition(x, y);
            this.x0 = x;
            this.y0 = y;
        }


    }

    public void restoreInk(double amount)
    {
        this.inkLimit = amount;
    }

    double inkCounter(int xStart, int xEnd, int yStart, int yEnd){
        double count = 0.0;
        count = Math.sqrt(Math.pow(xStart - xEnd, 2.0) + Math.pow(yStart - yEnd, 2.0));
        return count;
    }
    void setBool(){
        this.bool = false;
    }
    boolean getBool(){
        return this.bool;
    }
    @Override
    public void operateTo(int x1, int y1) {

        double wasted = inkCounter(this.x0, x1, this.y0, y1);

        if((inkLimit-wasted) < 0) {
            this.logger.info("You don't have enough ink to do this!");
            if(!bool){
                pub.notifyObservers();
                bool = true;
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
}
