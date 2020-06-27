package edu.kis.powp.jobs2d.command.historyComponent;

import edu.kis.powp.observer.Subscriber;

public class HistoryWindowCommandChangeObserver implements Subscriber {

    private HistoryManager backLogManager;
    public HistoryWindowCommandChangeObserver(HistoryManager commandManager) {
        super();
        this.backLogManager = commandManager;
    }

    @Override
    public void update() {
        backLogManager.updateCommandListField();
    }
}
