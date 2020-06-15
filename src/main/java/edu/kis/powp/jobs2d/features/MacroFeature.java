package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.macro.MacroDriverDecorator;

public class MacroFeature {
    private static MacroDriverDecorator macroDriverDecorator;
    /**Setup MacroDriver*/
    public static void setupMacroDriver() {
        macroDriverDecorator = new MacroDriverDecorator();
    }
    /**Get MacroDriver
     * @return macroDriverDecorator*/
    public static MacroDriverDecorator getMacroDriverDecorator() {
        return macroDriverDecorator;
    }
}
