package edu.kis.powp.jobs2d.events;


import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroDriverDecorator;
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
        MacroDriverDecorator manager = MacroFeature.getMacroDriverDecorator();
        if(manager.getCommands().size()>0)
            manager.clearMacro();
        this.driverManager.setCurrentDriver(manager.getPreviousDriver());
    }
}