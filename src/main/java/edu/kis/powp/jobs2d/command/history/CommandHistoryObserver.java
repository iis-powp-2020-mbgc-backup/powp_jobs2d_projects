package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.jobs2d.command.gui.CommandManager;
import edu.kis.powp.observer.Subscriber;

public class CommandHistoryObserver implements Subscriber {

    private CommandManager commandManager;

    public CommandHistoryObserver(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void update() {
        CommandHistory.addCommandEntry(commandManager.getCurrentCommandString());
    }

}
