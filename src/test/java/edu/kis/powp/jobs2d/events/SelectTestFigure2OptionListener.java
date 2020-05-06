package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.command.manager.MacroCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.MacroFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

public class SelectTestFigure2OptionListener implements ActionListener {

	private DriverManager driverManager;

	public SelectTestFigure2OptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FiguresJoe.figureScript2(driverManager.getCurrentDriver());
		MacroCommandManager manager = MacroFeature.getDriverCommandManager();
		FiguresJoe.figureScript2(manager);
		manager.setCurrentCommand(manager.getCommands(),"FigureJoe2");
	}
}