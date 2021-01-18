package edu.kis.powp.jobs2d.drivers.extensions;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.*;

public class ExtensionDriverComposite implements ExtensionDriver {
    private Map<String, ExtensionDriver> extensions = new HashMap<>();
    private Job2dDriver driver;

    public ExtensionDriverComposite() {
    }

    @Override
    public void setPosition(int i, int i1) {
        extensions.forEach((s, extensionDriver) -> {
            extensionDriver.setDriver(driver);
            extensionDriver.setPosition(i, i1);
        });
    }

    @Override
    public void operateTo(int i, int i1) {
        extensions.forEach((s, extensionDriver) -> {
            extensionDriver.setDriver(driver);
            extensionDriver.operateTo(i, i1);
        });
    }

    public void addOrRemoveExtension(ExtensionDriver extension, String extensionName) {
        if(extensions.containsKey(extensionName)) {
            extensions.remove(extensionName);
        } else {
            extensions.put(extensionName, extension);
        }
    }

    public Map<String, ExtensionDriver> getExtensions() {
        return extensions;
    }

    /**
     * @return Current driver.
     */
    public synchronized Job2dDriver getCurrentDriver() {
        if(extensions.size() == 0) {
            return driver;
        }
        return this;
    }

    @Override
    public void setDriver(Job2dDriver driver) {
        this.driver = driver;
    }
}
