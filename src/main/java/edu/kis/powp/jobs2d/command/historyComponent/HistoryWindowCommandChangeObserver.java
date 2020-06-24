package edu.kis.powp.jobs2d.command.historyComponent;

import edu.kis.powp.observer.Subscriber;

public class HistoryWindowCommandChangeObserver implements Subscriber {

    private HistoryManagerWindow backLogManagerWindow;
    public HistoryWindowCommandChangeObserver(HistoryManagerWindow commandManagerWindow) {
        super();
        this.backLogManagerWindow = commandManagerWindow;
    }

    @Override
    public void update() {
        backLogManagerWindow.updateCommandListField();
    }
}
