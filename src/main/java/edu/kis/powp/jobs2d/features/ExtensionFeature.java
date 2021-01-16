package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.events.SelectExtensionMenuOption;

public class ExtensionFeature {
    private static DriverManager driverManager = new DriverManager();
    private static Application app;

    /**
     * Setup jobs2d drivers Plugin and add to application.
     *
     * @param application Application context.
     */
    public static void setupExtension(Application application) {
        app = application;
        app.addComponentMenu(ExtensionFeature.class, "Extension");
    }

    /**
     * Add driver to context, create button in driver menu.
     *
     * @param name   Button name.
     * @param driver Job2dDriver object.
     */
    public static void addExtension(String name, Job2dDriver driver) {
        SelectExtensionMenuOption listener = new SelectExtensionMenuOption(driver, driverManager);
        app.addComponentMenuElement(ExtensionFeature.class, name, listener);
    }

    public static DriverManager getDriverManager() {
        return driverManager;
    }
}
