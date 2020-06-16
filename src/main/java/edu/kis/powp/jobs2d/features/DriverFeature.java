package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.drivers.adapter.TransformationAdapter;
import edu.kis.powp.jobs2d.features.Transformations.FlipTransformation;
import edu.kis.powp.jobs2d.features.Transformations.RotateTransformation;
import edu.kis.powp.jobs2d.features.Transformations.ScaleTransformation;

import java.util.HashMap;
import java.util.Map;

public class DriverFeature {

	private static DriverManager driverManager = new DriverManager();
	private static Application app;
	private static Map<String, SelectDriverMenuOptionListener> drivers = new HashMap<>();

	public static DriverManager getDriverManager() {
		return driverManager;
	}

	/**
	 * Setup jobs2d drivers Plugin and add to application.
	 * 
	 * @param application Application context.
	 */
	public static void setupDriverPlugin(Application application) {
		app = application;
		app.addComponentMenu(DriverFeature.class, "Drivers");
	}

	/**
	 * Add driver to context, create button in driver menu.
	 * 
	 * @param name   Button name.
	 * @param driver Job2dDriver object.
	 */
	public static void addDriver(String name, Job2dDriver driver) {

		if(drivers.containsKey(name)){
			drivers.get(name).setDriver(driver);
		}
		else{
			SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(driver, driverManager);
			app.addComponentMenuElement(DriverFeature.class, name, listener);
			drivers.put(name,listener);
		}

	}

	/**
	 * Update driver info.
	 */
	public static void updateDriverInfo() {
		app.updateInfo(driverManager.getCurrentDriver().toString());
		DriverFeature.addDriver("scale 2x", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new ScaleTransformation(2)));
		DriverFeature.addDriver("scale 0.5x", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new ScaleTransformation(0.5)));
		DriverFeature.addDriver("rotate 30", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new RotateTransformation(30)));
		DriverFeature.addDriver("flip horizontal", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new FlipTransformation(false)));
		DriverFeature.addDriver("flip vertical", new TransformationAdapter(DriverFeature.getDriverManager().getCurrentDriver(), new FlipTransformation(true)));
	}

}
