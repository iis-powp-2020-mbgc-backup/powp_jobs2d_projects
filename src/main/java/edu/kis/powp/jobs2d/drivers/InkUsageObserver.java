package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class InkUsageObserver implements Subscriber {

    private InkUsageDriver previousIUD;

    @Override public void update()
    {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        InkUsageDriver driver;

        if(previousIUD != null)
        {
            driver = new InkUsageDriver(currentDriver, previousIUD.getInkLimit(), previousIUD.getTotalUsage());
        }
        else
            driver = new InkUsageDriver(currentDriver, 10000f);

        DriverFeature.getDriverManager().setCurrentDriver(driver);
        System.out.println(driver.getInkLimit());
        previousIUD = driver;
    }
}
