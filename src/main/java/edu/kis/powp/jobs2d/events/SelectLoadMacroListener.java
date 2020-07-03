package edu.kis.powp.jobs2d.events;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.macro.MacroAdapter;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelectLoadMacroListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverManager driver = DriverFeature.getDriverManager();
        DriverCommandManager commandManager = CommandsFeature.getDriverCommandManager();
        CommandsFeature.getDriverCommandManager().setCurrentCommand((List<DriverCommand>) commandManager.getCurrentCommand(), "Macro");
        DriverFeature.getDriverManager().setCurrentDriver(driver.getCurrentDriver());
    }
}
