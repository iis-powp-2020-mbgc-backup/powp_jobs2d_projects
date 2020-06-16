package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.observer.Subscriber;

public class InkUsageObserver implements Subscriber {
    private Job2dDriver driver;

    public InkUsageObserver(Job2dDriver driver){
        this.driver = driver;
    }

    @Override
    public void update() {
    AddInkWindow addinkwindow = new AddInkWindow();
    }
}
