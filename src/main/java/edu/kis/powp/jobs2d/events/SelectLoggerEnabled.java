package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoggerEnabled implements ActionListener {
    private DriverManager driverManager;

    public SelectLoggerEnabled(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        driverManager.getChangePublisher().notifyObservers();
    }
}
