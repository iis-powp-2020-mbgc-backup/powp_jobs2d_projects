package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.observer.Subscriber;

public class LoadedCommandStatisticObserver implements Subscriber {

    private LoadedCommandStatisticWindow statsWindow;

    public LoadedCommandStatisticObserver(LoadedCommandStatisticWindow statsWindow) {
        this.statsWindow = statsWindow;
    }

    @Override
    public void update() {
        statsWindow.updateTextArea();
    }
}
