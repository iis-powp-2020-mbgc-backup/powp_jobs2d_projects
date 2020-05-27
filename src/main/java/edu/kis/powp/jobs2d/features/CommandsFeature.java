package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;

public class CommandsFeature {

	private static DriverCommandManager commandManager;

	public static void setupCommandManager() {
		commandManager = new DriverCommandManager();
		commandManager.setDriverManager(DriverFeature.getDriverManager());
		LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
		commandManager.getChangePublisher().addSubscriber(loggerObserver);
	}

	/**
	 * Get manager of application driver command.
	 * 
	 * @return plotterCommandManager.
	 */
	public static DriverCommandManager getDriverCommandManager() {
		return commandManager;
	}

	/**
	 *
	 * @param driverCommand driverCommand to copy
	 * @return deep copy of driverCommand
	 * @throws CloneNotSupportedException
	 */
	public static DriverCommand deepCommandCopy(DriverCommand driverCommand) throws CloneNotSupportedException{
		return driverCommand.clone();
	}
}
