package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.macro.MacroAdapter;


public class MacroFeature {
    private static MacroAdapter macroDriver;

    public static void setMacroAdapter() {
        macroDriver = new MacroAdapter();
    }

    public static MacroAdapter getMacroAdapter() {
        return macroDriver;
    }
}