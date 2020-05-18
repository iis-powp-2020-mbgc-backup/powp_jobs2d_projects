package edu.kis.powp.jobs2d.events;


import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroDriver;
import edu.kis.powp.jobs2d.features.MacroFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectClearMacro implements ActionListener {
    private DriverManager driverManager;
    public SelectClearMacro(DriverManager driverManager){
        this.driverManager=driverManager;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MacroDriver manager = MacroFeature.getMacroDriver();
        manager.clearMacro();
        this.driverManager.setCurrentDriver(manager.getPreviousDriver());
    }
}