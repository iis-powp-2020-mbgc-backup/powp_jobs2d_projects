package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.macro.MacroDriver;

public class MacroFeature {
    private static MacroDriver macroDriver;

    public static void setMacroDriver() {
        macroDriver = new MacroDriver();
    }

    public static MacroDriver getMacroDriver() {
        return macroDriver;
    }
}
