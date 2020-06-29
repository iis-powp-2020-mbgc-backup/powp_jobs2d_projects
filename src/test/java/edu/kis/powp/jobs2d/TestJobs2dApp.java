package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;

import edu.kis.powp.jobs2d.command.history.CommandHistory;
import edu.kis.powp.jobs2d.command.history.CommandHistoryObserver;
import edu.kis.powp.jobs2d.command.history.HistoryViewer;
import edu.kis.powp.jobs2d.command.gui.*;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.*;
import edu.kis.powp.jobs2d.features.Readers.Reader;
import edu.kis.powp.jobs2d.features.Readers.SimpleFormatReader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJobs2dApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static CommandManagerWindowCommandChangeObserver windowObserver;

	/**
	 * Setup test concerning preset figures in context.
	 *
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager());
		SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
				DriverFeature.getDriverManager());
		SelectAddCommandManagerWindowCommandChangeObserver selectAddCommandManagerWindow =
				new SelectAddCommandManagerWindowCommandChangeObserver();

		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
		application.addTest("Deep copy", new ICompoundCommandDeepCopyTest(DriverFeature.getDriverManager()));
		application.addTest("Add Logger Command Change Observer", new SelectAddLoggerCommandChangeObserver());
		application.addTest("Add Command Manager Window Change Observer", selectAddCommandManagerWindow);
	}

	/**
	 * Setup test using driver commands in context.
	 *
	 * @param application Application context.
	 */
	private static void setupCommandTests(Application application) {
		application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());

		application.addTest("Mouse figure", new SelectMouseFigureOptionListener(application.getFreePanel(), DriverFeature.getDriverManager()));

		application.addTest("Start Macro", new SelectStartMacroListener());
		application.addTest("Stop Macro", new SelectStopMacroListener());
		application.addTest("Load Macro",new SelectLoadMacroDriverListener());
		application.addTest("Clear Macro",new SelectClearMacroListener());
		application.addTest("Clear History", new SelectClearHistoryListener());
		application.addTest("Run custom command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

		application.addTest("Calculate statistics of current command", new CalculateStatisticListener());
	}

	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 *
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger driver", loggerDriver);

		DrawPanelController drawerController = DrawerFeature.getDrawerController();
		Job2dDriver basicLineDriver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		DriverFeature.addDriver("Line Simulator", basicLineDriver);
		DriverFeature.getDriverManager().setCurrentDriver(basicLineDriver);

		Job2dDriver specialLineDriver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		DriverFeature.addDriver("Special line Simulator", specialLineDriver);

		Job2dDriver dottedLineDriver = new LineDriverAdapter(drawerController, LineFactory.getDottedLine(), "dotted");
		DriverFeature.addDriver("Dotted line Simulator", dottedLineDriver);


    DriverInfoChangeObserver driverInfoChangeObserver = new DriverInfoChangeObserver();

		DriverFeature.getDriverManager().getPublisher().addSubscriber(driverInfoChangeObserver);

		DriverFeature.getDriverManager().getPublisher().addSubscriber(new DriverChangeObserver());

		TransformationChangeObserver transformationChangeObserver = new TransformationChangeObserver();
		DriverFeature.getDriverManager().getPublisher().addSubscriber(transformationChangeObserver);

		DriverFeature.updateDriverInfo();
	}

	private static void setupWindows(Application application) {

		CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("Command Manager", commandManager);

		CommandManagerWindowObserverChangeObserver windowObserverChangeObserver =
				new CommandManagerWindowObserverChangeObserver(commandManager);
		CommandsFeature.getDriverCommandManager().addObserverChangeSubscriber(windowObserverChangeObserver);
		Reader reader = new SimpleFormatReader();
		CommandImportWindow commandImportWindow = new CommandImportWindow(CommandsFeature.getDriverCommandManager(), reader);
		application.addWindowComponent("Editor", commandImportWindow);

		HistoryViewer historyViewer = new HistoryViewer(CommandHistory.getEntryHistoryList(), CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("History", historyViewer);
		CommandHistoryObserver commandHistoryObserver = new CommandHistoryObserver(CommandsFeature.getDriverCommandManager());
		CommandsFeature.getDriverCommandManager().addChangeSubscriber(commandHistoryObserver);

    	CommandTransformationWindow commandTransformationWindow = new CommandTransformationWindow(CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("Transformation", commandTransformationWindow);

		ComplexCommandEditorWindow complexCommandEditorWindow =
				new ComplexCommandEditorWindow();
		application.addWindowComponent("Complex command editor", complexCommandEditorWindow);

		ComplexCommandEditorWindowCommandChangeObserver editorWindowCommandChangeObserver =
				new ComplexCommandEditorWindowCommandChangeObserver(complexCommandEditorWindow);
		CommandsFeature.getDriverCommandManager().addChangeSubscriber(editorWindowCommandChangeObserver);

		windowObserver = new CommandManagerWindowCommandChangeObserver(commandManager);
		CommandsFeature.getDriverCommandManager().addChangeSubscriber(windowObserver);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 *
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {

		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	public static CommandManagerWindowCommandChangeObserver getWindowObserver() {
		return windowObserver;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				DrawerFeature.setupDrawerPlugin(app, app.getFreePanel());
				CommandsFeature.setupCommandManager();
				MacroFeature.setupMacroDriver();
				DriverFeature.setupDriverPlugin(app);
			  DriverCompositeFeature.setupDriverComposite();

				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);

				app.setVisibility(true);
			}
		});
	}

}
