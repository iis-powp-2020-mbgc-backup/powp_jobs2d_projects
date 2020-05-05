package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

public class SelectTestFigure2OptionListener implements ActionListener {

	private DriverManager driverManager;

	public SelectTestFigure2OptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FiguresJoe.figureScript2(driverManager.getCurrentDriver());
		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		FiguresJoe.figureScript2(manager);
		manager.setCurrentCommand(manager.getCommands(),"FigureJoe2");

	}
}