package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.macro.MacroDriverDecorator;


public class MacroFeature {
    private static MacroDriverDecorator macroDriver;

    public static void setupMacroDriverDecorator() {
        macroDriver = new MacroDriverDecorator();
    }

    public static MacroDriverDecorator getMacroDriverDecorator() {
        return macroDriver;
    }
}