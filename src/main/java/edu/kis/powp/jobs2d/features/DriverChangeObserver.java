package edu.kis.powp.jobs2d.features;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.MacroDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeObserver implements Subscriber {

    @Override
    public void update() {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();

        if (currentDriver instanceof MacroDriver) {
            DriverComposite driverComposite = new DriverComposite();

            DrawPanelController drawerController = DrawerFeature.getDrawerController();
            Job2dDriver basicLineDriver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");

            driverComposite.addDriver(currentDriver);
            driverComposite.addDriver(basicLineDriver);

            DriverFeature.getDriverManager().setCurrentDriver(driverComposite);
        }
    }
}
