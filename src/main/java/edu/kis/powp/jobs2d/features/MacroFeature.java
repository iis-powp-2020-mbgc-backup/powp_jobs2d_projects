package edu.kis.powp.jobs2d.features;


import edu.kis.powp.jobs2d.drivers.MacroDriverDecorator;

public class MacroFeature {

    private static MacroDriverDecorator macroDriverDecorator = new MacroDriverDecorator();

    /**
     * Get manager of application driver macro.
     *
     * @return macroDriverDecorator.
     */
    public static MacroDriverDecorator getMacroDriverDecorator() {
        return macroDriverDecorator;
    }
}
