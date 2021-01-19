package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverLabelChangeObserver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.extensions.ExtensionDriver;
import edu.kis.powp.jobs2d.drivers.extensions.ExtensionDriverComposite;
import edu.kis.powp.jobs2d.events.SelectExtensionMenuOption;

public class ExtensionFeature {
    private static ExtensionDriverComposite extensionDriverDecorator;
    private static DriverManager driverManager = DriverFeature.getDriverManager();
    private static Application app;

    /**
     * Setup jobs2d drivers Plugin and add to application.
     *
     * @param application Application context.
     */
    public static void setupExtension(Application application) {
        app = application;
        app.addComponentMenu(ExtensionFeature.class, "Extension");
        driverManager.getChangePublisher().addSubscriber(new DriverLabelChangeObserver());
        extensionDriverDecorator = new ExtensionDriverComposite();
    }

    /**
     * Add driver to context, create button in driver menu.
     *
     * @param name   Button name.
     */
    public static void addExtension(String name, ExtensionDriver extensionDriver) {
        SelectExtensionMenuOption listener = new SelectExtensionMenuOption(driverManager, extensionDriver);
        app.addComponentMenuElementWithCheckBox(ExtensionFeature.class, name, listener, false);
    }

    /**
     * Update driver info.
     */
    public static void updateDriverInfo() {
        app.updateInfo(driverManager.getCurrentDriver().toString());
    }
}
