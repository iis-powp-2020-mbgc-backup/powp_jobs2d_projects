package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class CommandFactory {

    public DriverCommand getCommand(String commandType, int x, int y) {
        if (commandType == null) {
            return null;
        }

        if (commandType.equalsIgnoreCase("SET POSITION")) {
            return new SetPositionCommand(x, y);
        } else if (commandType.equalsIgnoreCase("OPERATE TO")) {
            return new OperateToCommand(x, y);
        }

        return null;
    }
}
