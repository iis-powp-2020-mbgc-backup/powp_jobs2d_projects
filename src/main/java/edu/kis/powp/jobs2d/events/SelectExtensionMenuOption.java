package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectExtensionMenuOption implements ActionListener {
    private DriverManager driverManager;
    private Job2dDriver driver = null;

    public SelectExtensionMenuOption(Job2dDriver driver, DriverManager driverManager) {
        this.driverManager = driverManager;
        this.driver = driver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //driverManager.setCurrentDriver(driver);
    }
}
