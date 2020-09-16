package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.macro.MacroDriverDecorator;
import edu.kis.powp.jobs2d.drivers.macro.MacroDriverObserver;


public class MacroFeature {
    private static MacroDriverDecorator macroDriver;

    public static void setupMacroDriverDecorator() {
        macroDriver = new MacroDriverDecorator();
        MacroDriverObserver macroDriverObserver = new MacroDriverObserver();
        DriverFeature.getDriverManager().getChangePublisher().addSubscriber(macroDriverObserver);
    }

    public static MacroDriverDecorator getMacroDriverDecorator() {
        return macroDriver;
    }
}