package edu.kis.powp.jobs2d.drivers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;

public class SelectDriverMenuOptionListener implements ActionListener {
    private DriverManager driverManager;
    private Job2dDriver driver = null;
    private DriverStatistics statistics;

    public SelectDriverMenuOptionListener(Job2dDriver driver, DriverManager driverManager, DriverStatistics statistics) {
        this.driverManager = driverManager;
        this.driver = driver;
        this.statistics = statistics;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        driverManager.setCurrentDriver(driver, statistics);
        DriverFeature.updateDriverInfo();}
}
