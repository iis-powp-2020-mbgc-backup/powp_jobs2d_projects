package edu.kis.powp.jobs2d.command.historyComponent;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;

public class HistoryFeature {

    public static void setupHistoryFeature(Application application) {
        HistoryManager history = new HistoryManager(CommandsFeature.getDriverCommandManager());
        HistoryManagerWindow historyWindow = new HistoryManagerWindow(CommandsFeature.getDriverCommandManager());
        HistoryWindowCommandChangeObserver historyObserver = new HistoryWindowCommandChangeObserver(history);
        CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(historyObserver);

        application.addComponentMenu(HistoryManagerWindow.class, "History");
        application.addComponentMenuElement(HistoryManagerWindow.class, "Show History Window", (ActionEvent e) -> historyWindow.changeWindowVisibility());
        application.addComponentMenuElement(HistoryManagerWindow.class, "Clear History",  (ActionEvent e) -> history.clearHistoryOfCommand());
    }
}
