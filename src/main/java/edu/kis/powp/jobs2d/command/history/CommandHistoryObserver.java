package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

public class CommandHistoryObserver implements Subscriber {

    private DriverCommandManager driverCommandManager;

    public CommandHistoryObserver(DriverCommandManager commandManager) {
        this.driverCommandManager = commandManager;
    }

    @Override
    public void update() {
        CommandHistory.addCommandEntry(driverCommandManager.getCurrentCommandString(),
                driverCommandManager.getCurrentCommand());
    }

}
