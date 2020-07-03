package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverCompositeFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectStartMacroListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!MacroFeature.isEnabled()) {
            MacroFeature.setIsEnabled(true);
            DriverManager driverManager = DriverFeature.getDriverManager();

            DriverCompositeFeature.setDrawingDriver(driverManager.getCurrentDriver());

            driverManager.setCurrentDriver(DriverCompositeFeature.getDriverComposite());
        }
    }
}
