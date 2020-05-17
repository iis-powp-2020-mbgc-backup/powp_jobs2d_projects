package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;

public class CommandsFeature {

	private static DriverCommandManager commandManager;
	private static LoggerCommandChangeObserver loggerObserver;

	public static void setupCommandManager() {
		commandManager = new DriverCommandManager();

		loggerObserver = new LoggerCommandChangeObserver();
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
	public static LoggerCommandChangeObserver getLoggerObserver() {
		return loggerObserver;
	}
}
