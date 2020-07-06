package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectStopMacroListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        Job2dDriver driver = MacroFeature.getMacroDriverDecorator().getDriver();
        DriverFeature.getDriverManager().setCurrentDriver(driver);
    }

}

