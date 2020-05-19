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

	private Integer halfWidth;
	private Integer halfHeight;
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
		setXYnull();
		halfWidth = drawPanel.getWidth()/2;
		halfHeight = drawPanel.getHeight()/2;

		List<DriverCommand> commands = new ArrayList<>();
		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.clearCurrentCommand();
		manager.setCurrentCommand(commands, "Mouse Command");
		this.drawPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(prevX == null || prevY == null){
					updatePreviousMousePosition(e);
				}

				commands.add(new SetPositionCommand(prevX, prevY));
				commands.add(new OperateToCommand(e.getX()- halfWidth, e.getY()- halfHeight));

				updatePreviousMousePosition(e);

				manager.getCurrentCommand().execute(driverManager.getCurrentDriver());
			}
		});
	}

	private void updatePreviousMousePosition(MouseEvent event){
		prevX = event.getX() - halfWidth;
		prevY = event.getY() - halfHeight;
	}

	private void setXYnull(){
		prevY = null;
		prevX = null;
	}
}
