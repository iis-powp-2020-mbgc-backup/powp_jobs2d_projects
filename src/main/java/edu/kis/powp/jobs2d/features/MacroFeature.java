package edu.kis.powp.jobs2d.features;


import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;
import edu.kis.powp.jobs2d.drivers.MacroDriver;

public class MacroFeature {

    private static MacroDriver macroDriver= new MacroDriver();

    /**
     * Get manager of application driver macro.
     *
     * @return macroDriver.
     */
    public static MacroDriver getMacroDriver() {
        return macroDriver;
    }
}
