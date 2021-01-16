package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.LoadedCommandStatisticWindow;
import edu.kis.powp.jobs2d.command.gui.LoadedCommandStatisticObserver;

import java.awt.event.ActionEvent;

public class LoadedCommandStatisticFeature {

    public static void setupStatsFeature(Application application) {
        LoadedCommandStatisticWindow statsWindow = new LoadedCommandStatisticWindow(CommandsFeature.getDriverCommandManager());
        LoadedCommandStatisticObserver statsObserver = new LoadedCommandStatisticObserver(statsWindow);
        CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(statsObserver);

        application.addComponentMenu(LoadedCommandStatisticWindow.class, "Statistic");
        application.addComponentMenuElement(LoadedCommandStatisticWindow.class, "Show stats", (ActionEvent e) -> statsWindow.changeWindowVisibility());
    }
}
