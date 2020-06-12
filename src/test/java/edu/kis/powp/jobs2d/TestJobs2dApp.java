package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.gui.backlog.BackLogManagerWindow;
import edu.kis.powp.jobs2d.command.gui.backlog.BackLogManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.command.gui.UsageMonitorWindow;

import edu.kis.powp.jobs2d.events.SelectLoadSecretCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigure2OptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.events.SelectClearMacro;

import edu.kis.powp.jobs2d.events.SelectLoadMacro;
import edu.kis.powp.jobs2d.features.MacroFeature;

import edu.kis.powp.jobs2d.drivers.DriverChangeTitleObserver;
import edu.kis.powp.jobs2d.drivers.DriverUsageMonitorObserver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestJobs2dApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
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
		
		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
		
	}
	
	/**
	 * Setup test using driver commands in context.
	 *
	 * @param application Application context.
	 */
	private static void setupCommandTests(Application application) {
		application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());
		application.addTest("Load from file", new SelectImportCommandFromFileOptionListener());
		application.addTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

		application.addTest("Load macro", new SelectLoadMacro(DriverFeature.getDriverManager(), MacroFeature.getMacroDriverDecorator(), CommandsFeature.getDriverCommandManager()));
		application.addTest("Clear macro", new SelectClearMacro(DriverFeature.getDriverManager()));
		application.addTest("DriverCommandVisitor test DriverCommand", new SelectSingleCommandVisitorTestListener());
		application.addTest("DriverCommandVisitor test ICompoundCommand", new SelectCompoundCommandVisitorTestListener());
		
		application.addTest("Load and copy secret command", new SelectCopySecretCommand());

		application.addTest("Vertical flipped secret figure", new FlipVerticalSecretCommandOptionListener());
		application.addTest("Horizontal flipped figure", new FlipHorizontalSecretCommandOptionListener());
		application.addTest("Scaling figure", new ScaleCommandOptionListener());
		application.addTest("Rotate LEFT figure", new RotateLeftSecretCommandOptionListener());
		application.addTest("Rotate RIGHT figure", new RotateRightSecretCommandOptionListener());
	}
	
	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 *
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {

		DriverFeature.addDriver("Macro", MacroFeature.getMacroDriverDecorator());

		Job2dDriver loggerDriver = new LoggerDriver();
		
		DriverChangeTitleObserver driverObserver = new DriverChangeTitleObserver();
		DriverFeature.getDriverManager().getChangePublisher().addSubscriber(driverObserver);
		
		DriverFeature.addDriver("Logger driver", loggerDriver);
		
		DrawPanelController drawerController = DrawerFeature.getDrawerController();
		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		DriverFeature.addDriver("Line Simulator", driver);
		DriverFeature.getDriverManager().setCurrentDriver(driver);
		MacroFeature.getMacroDriverDecorator().setCoreDriver(DriverFeature.getDriverManager().getCurrentDriver());
		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		DriverFeature.addDriver("Special line Simulator", driver);
	}
	
	private static void setupWindows(Application application) {
		
		CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("Command Manager", commandManager);
		
		BackLogManagerWindow backLog = new BackLogManagerWindow(CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("Back Log", backLog);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManager);
		CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);

		BackLogManagerWindowCommandChangeObserver windowObserver2 = new BackLogManagerWindowCommandChangeObserver(backLog);
		CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver2);
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
	
	/**
	 * Setup a usage monitor window, which displays a current head distance.
	 *
	 * @param app Application context.
	 */
	private static void setupUsageMonitor(Application app) {
		UsageMonitorWindow usageMonitorWindow = new UsageMonitorWindow();
		app.addWindowComponent("Usage Monitor", usageMonitorWindow);
		DriverUsageMonitorObserver driverUsageMonitorObserver = new DriverUsageMonitorObserver(usageMonitorWindow);
		DriverFeature.getDriverManager().getChangePublisher().addSubscriber(driverUsageMonitorObserver);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				DrawerFeature.setupDrawerPlugin(app);
				MouseDrawFeature.SetMouseListener(app.getFreePanel());
				AnalyzerFeature.setUpAnalyzer();
				CommandsFeature.setupCommandManager();
        
				DriverFeature.setupDriverPlugin(app);
				setupUsageMonitor(app);
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
