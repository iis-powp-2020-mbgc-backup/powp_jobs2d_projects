package edu.kis.powp.jobs2d.features;


import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;
import edu.kis.powp.jobs2d.command.manager.MacroCommandManager;

public class MacroFeature {

    private static MacroCommandManager macroManager;

    public static void setupMacroManager() {
        macroManager = new MacroCommandManager();

        LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
        macroManager.getChangePublisher().addSubscriber(loggerObserver);
    }

    /**
     * Get manager of application driver command.
     *
     * @return plotterCommandManager.
     */
    public static MacroCommandManager getDriverCommandManager() {
        return macroManager;
    }
}
