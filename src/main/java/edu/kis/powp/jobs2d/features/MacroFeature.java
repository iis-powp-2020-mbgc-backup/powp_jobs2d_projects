package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.macro.MacroDriverAdapter;

public class MacroFeature {
    private static MacroDriverAdapter macroDriverAdapter;

    public static void setupMacroDriver() {
        macroDriverAdapter = new MacroDriverAdapter();
    }

    public static MacroDriverAdapter getMacroDriverAdapter() {
        return macroDriverAdapter;
    }
}
