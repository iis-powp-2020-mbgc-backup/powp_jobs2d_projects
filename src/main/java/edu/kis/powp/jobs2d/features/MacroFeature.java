package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.macro.MacroDriverDecorator;

public class MacroFeature {
    private static MacroDriverDecorator macroDriverDecorator;

    public static void setupMacroDriver() {
        macroDriverDecorator = new MacroDriverDecorator();
    }

    public static MacroDriverDecorator getMacroDriverDecorator() {
        return macroDriverDecorator;
    }
}
