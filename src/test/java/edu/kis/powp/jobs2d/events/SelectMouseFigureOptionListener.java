package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectMouseFigureOptionListener implements ActionListener {

	public static final int MOUSE_POSITION_X = 270;
	public static final int MOUSE_POSITION_Y = 232;
	private DriverManager driverManager;
	private JPanel drawPanel;
	private Integer prevX;
	private Integer prevY;


	public SelectMouseFigureOptionListener(JPanel drawPanel, DriverManager driverManager) {
		this.drawPanel = drawPanel;
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		List<DriverCommand> commands = new ArrayList<>();
		this.drawPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(prevX == null || prevY == null){
					updatePreviousMousePosition(e);
				}

				commands.add(new SetPositionCommand(prevX, prevY));
				commands.add(new OperateToCommand(e.getX()- MOUSE_POSITION_X, e.getY()- MOUSE_POSITION_Y));

				updatePreviousMousePosition(e);

				commands.forEach(command -> command.execute(driverManager.getCurrentDriver()));
			}
		});
		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(commands, "Mouse Command");
	}

	private void updatePreviousMousePosition(MouseEvent event){
		prevX = event.getX() - MOUSE_POSITION_X;
		prevY = event.getY() - MOUSE_POSITION_Y;
	}
}
