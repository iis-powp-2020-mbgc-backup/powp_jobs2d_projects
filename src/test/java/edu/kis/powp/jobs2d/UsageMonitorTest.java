package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.UsageMonitor;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class UsageMonitorTest implements ActionListener {

    private Job2dDriver driver;

    public UsageMonitorTest(Job2dDriver driver)
    {
        this.driver = driver;
    }

    public UsageMonitorTest() {

    }

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        logger.info("Testing Usage Monitor");

        UsageMonitor um = new UsageMonitor(this.driver);

        DriverCommandManager driverCommandManager = CommandsFeature.getDriverCommandManager();

        DriverCommand driverCommand = driverCommandManager.getCurrentCommand();
        List<DriverCommand> driverCommands = new ArrayList<>();
        driverCommands.add(driverCommand);

        ICompoundCommand compound = new CompoundCommand(driverCommands);

        compound.execute(driver);
        compound.accept(um);

        logger.info("Operate distance: " + um.getOperateToDistance() + "\nHead distance: " + um.getPositionDistance());
    }
}
