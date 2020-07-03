package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverCompositeFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectStopMacroListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MacroFeature.isEnabled()) {
            MacroFeature.setIsEnabled(false);
            DriverManager driverManager = DriverFeature.getDriverManager();
            driverManager.setCurrentDriver(DriverCompositeFeature.getDrawingDriver());
        }
    }
}
