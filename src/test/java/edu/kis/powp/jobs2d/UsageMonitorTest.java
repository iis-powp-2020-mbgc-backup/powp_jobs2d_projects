package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.*;
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

        List<DriverCommand> driverCommands = new ArrayList<>();
        driverCommands.add(new SetPositionCommand(-20, -50));
        driverCommands.add(new OperateToCommand(-20, -50));
        driverCommands.add(new SetPositionCommand(-20, -40));
        driverCommands.add(new OperateToCommand(-20, 50));
        driverCommands.add(new SetPositionCommand(0, -50));
        driverCommands.add(new SetPositionCommand(-20, -50));
        driverCommands.add(new OperateToCommand(-20, -50));
        driverCommands.add(new SetPositionCommand(-20, -40));
        driverCommands.add(new OperateToCommand(-20, 50));
        driverCommands.add(new SetPositionCommand(0, -50));
        driverCommands.add(new OperateToCommand(0, -50));
        driverCommands.add(new SetPositionCommand(0, -40));
        driverCommands.add(new OperateToCommand(0, 50));

        ICompoundCommand compound = new ICompoundCommand() {
            List<DriverCommand> commands = driverCommands;

            @Override
            public DriverCommand clone() throws CloneNotSupportedException {
                return ICompoundCommand.super.clone();
            }

            @Override
            public Iterator<DriverCommand> iterator()
            {
                return commands.iterator();
            }

            @Override
            public void execute(Job2dDriver driver)
            {
                commands.forEach(c -> c.execute(driver));
            }
        };

        compound.execute(driver);
        compound.accept(um);

    }
}
