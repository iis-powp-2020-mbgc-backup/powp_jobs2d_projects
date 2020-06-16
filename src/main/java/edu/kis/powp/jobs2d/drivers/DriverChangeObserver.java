package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeObserver implements Subscriber {

    private InkUsageDriverAdapter previousIUD;
    Publisher driverPublisher = new Publisher();

    @Override public void update()
    {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        InkUsageDriverAdapter driver;
        //driverPublisher.clearObservers();
        if(previousIUD != null)
        {
           // driverPublisher.addSubscriber(new Publisher(driver));
            driver = new InkUsageDriverAdapter(currentDriver, previousIUD.getInkLimit(), previousIUD.getTotalUsage());
        }
        else {
            //driverPublisher.addSubscriber(new InkUsageObserver(currentDriver));
            driver = new InkUsageDriverAdapter(currentDriver, 10000f);
        }

        DriverFeature.getDriverManager().setCurrentDriver(driver);
        System.out.println(driver.getInkLimit());
        previousIUD = driver;
    }
}
