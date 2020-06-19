package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.command.gui.RefuelInkWindow;
import edu.kis.powp.observer.Subscriber;

public class NoInkObserver implements Subscriber {
    private InkUsageDriverDecorator driver;

    public NoInkObserver(InkUsageDriverDecorator driver){
        this.driver = driver;
    }

    @Override
    public void update() {
        RefuelInkWindow addinkwindow = new RefuelInkWindow(this.driver);
    }
}
