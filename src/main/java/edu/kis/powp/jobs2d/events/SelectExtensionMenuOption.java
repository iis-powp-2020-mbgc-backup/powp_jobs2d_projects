package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.extensions.ExtensionDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectExtensionMenuOption implements ActionListener {
    private DriverManager driverManager;
    private ExtensionDriver extensionDriver;

    public SelectExtensionMenuOption(DriverManager driverManager, ExtensionDriver extensionDriver) {
        this.driverManager = driverManager;
        this.extensionDriver = extensionDriver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        driverManager.getExtensionDriver().addOrRemoveExtension(extensionDriver, extensionDriver.toString());
    }
}
