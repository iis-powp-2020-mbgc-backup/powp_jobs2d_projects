package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.analyzer.CommandAnalyzerCommandChangeObserver;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;

public class CommandsFeature {

	private static DriverCommandManager commandManager;

	public static void setupCommandManager() {
		commandManager = new DriverCommandManager(DriverFeature.getDriverManager());

		LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
		CommandAnalyzerCommandChangeObserver analyzerCommandChangeObserver = new CommandAnalyzerCommandChangeObserver(AnalyzerFeature.getAnalyzer());

		commandManager.getChangePublisher().addSubscriber(analyzerCommandChangeObserver);
		commandManager.getChangePublisher().addSubscriber(loggerObserver);
		commandManager.setAnalyzer(AnalyzerFeature.getAnalyzer());
	}

	/**
	 * Get manager of application driver command.
	 * 
	 * @return plotterCommandManager.
	 */
	public static DriverCommandManager getDriverCommandManager() {
		return commandManager;
	}

	public static DriverCommand deepCopyCommand(DriverCommand toCopy) throws CloneNotSupportedException {
		return toCopy.clone();
	}
}
