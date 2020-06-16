package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.transformations.CoordsFlipTransformation;
import edu.kis.powp.jobs2d.drivers.transformations.CoordsRotateTransformation;
import edu.kis.powp.jobs2d.drivers.transformations.CoordsScaleTransformation;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

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

		application.addTest("Load macro command",new SelectLoadMacroListener());

		application.addTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));
		application.addTest("Deep secret command copy", new SelectDeepCopySecretCommand());
		application.addTest("Visitor Command Counting", new DriverCommandCounterVisitorTest());
		application.addTest("Visitor Command Execution", new DriverCommandExecutorVisitorTest(DriverFeature.getDriverManager().getCurrentDriver()));

		application.addTest("Rotate command 90 deg clockwise", new SelectCommandTransformationRotateOptionListener(90));
		application.addTest("Rotate command 90 deg counterclockwise", new SelectCommandTransformationRotateOptionListener(-90));
		application.addTest("Rotate command 30 deg clockwise", new SelectCommandTransformationRotateOptionListener(30));

		application.addTest("Scale command (2x, 1y)", new SelectScaleCommandOptionListener(2.0f, 1.0f));
		application.addTest("Scale command (1.5x, 2.3y)", new SelectScaleCommandOptionListener(1.5f, 2.3f));
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
		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		DriverFeature.addDriver("Line Simulator", driver);
		DriverFeature.getDriverManager().setCurrentDriver(driver);

		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		DriverFeature.addDriver("Special line Simulator", driver);

		DriverFeature.addDriver("Macro Driver (special line)", MacroFeature.getMacroDriverDecorator());
		MacroFeature.getMacroDriverDecorator().setDriver(driver);
		DriverFeature.updateDriverInfo();
	}

	private static void setupWindows(Application application) {

		CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("Command Manager", commandManager);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManager);
		CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);
	}

	private static void setupTransformations(Application application) {
		application.addComponentMenu(null, "Transformations at driver level");
		application.addComponentMenuElement(null,"Scaling", new SelectDriverTransformation(new CoordsScaleTransformation(1.5, 1)));

		application.addComponentMenuElement(null, "Rotating", new SelectDriverTransformation(new CoordsRotateTransformation(30)));

		application.addComponentMenuElement(null, "Flipping", new SelectDriverTransformation(new CoordsFlipTransformation(true)));
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				DrawerFeature.setupDrawerPlugin(app);
				CommandsFeature.setupCommandManager();
				MacroFeature.setupMacroDriver();

				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);
				setupTransformations(app);
				JPanel panel = app.getFreePanel();
				panel.addMouseListener(new MouseDrawListener(panel, DriverFeature.getDriverManager()));

                app.setVisibility(true);
			}
		});
	}

}
