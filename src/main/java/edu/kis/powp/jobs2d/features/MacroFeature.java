package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.MacroDriver;

public class MacroFeature {

    private static MacroDriver macroDriver;
    private static boolean isEnabled;

    public static void setIsEnabled(boolean isEnabled) {
        MacroFeature.isEnabled = isEnabled;
    }

    public static boolean isEnabled() {
        return isEnabled;
    }

    public static void setupMacroDriver() {
        macroDriver = new MacroDriver();
        isEnabled = false;
    }

    public static MacroDriver getMacroDriver() {
        return macroDriver;
    }
}
