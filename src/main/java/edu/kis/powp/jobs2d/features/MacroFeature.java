package edu.kis.powp.jobs2d.features;


import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;
import edu.kis.powp.jobs2d.drivers.MacroDriver;

public class MacroFeature {

    private static MacroDriver macroDriver= new MacroDriver();

    public static void setupMacroManager() {


        LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();

    }

    /**
     * Get manager of application driver command.
     *
     * @return plotterCommandManager.
     */
    public static MacroDriver getMacroDriver() {
        return macroDriver;
    }
}
