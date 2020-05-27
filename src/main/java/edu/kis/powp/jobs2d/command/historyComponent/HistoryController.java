package edu.kis.powp.jobs2d.command.historyComponent;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;

public class HistoryController {

    public static void setupHistoryController(Application application) {
        HistoryManagerWindow history = new HistoryManagerWindow(CommandsFeature.getDriverCommandManager());
        HistoryWindowCommandChangeObserver historyObserver = new HistoryWindowCommandChangeObserver(history);
        CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(historyObserver);

        application.addComponentMenu(HistoryManagerWindow.class, "History");
        application.addComponentMenuElement(HistoryManagerWindow.class, "Show History Window", (ActionEvent e) -> history.changeWindowVisibility());
        application.addComponentMenuElement(HistoryManagerWindow.class, "Clear History",  (ActionEvent e) -> history.clearHistoryOfCommand());
    }
}
