package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.MacroDriverComposite;

public class MacroFeature {

    private static MacroDriverComposite macroDriverComposite;

    public static void setupMacroDriverDecorator() {
        macroDriverComposite = new MacroDriverComposite();
    }

    public static MacroDriverComposite getMacroDriverComposite() {
        return macroDriverComposite;
    }
}
