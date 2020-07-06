package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandCoordinateChangerVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandListingVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;

public class CommandEditorWindow extends JFrame implements WindowComponent {

	private static final long serialVersionUID = 9204679248304669949L;
	private DriverCommandManager commandManager;
	private JTextArea currentCommandField;
	private JList<DriverCommand> commandPartsList;
	private JButton moveUp;
	private JButton moveDown;
	private JTextField coordX;
	private JTextField coordY;
	private JTextArea coordinatesX;
	private JTextArea coordinatesY;
	private JButton changeCoordinates;
	private DefaultListModel<DriverCommand> listModel;
	private CommandListingVisitor commandListingVisitor;
	private CommandCoordinateChangerVisitor commandCoordinateChangerVisitor;

	public CommandEditorWindow(DriverCommandManager commandManager) {
		commandCoordinateChangerVisitor = new CommandCoordinateChangerVisitor();
		this.setTitle("Command Editor");
		this.setMinimumSize(new Dimension(500, 600));
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());
		content.setBackground(Color.WHITE);
		this.commandManager = commandManager;

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.weightx = 1;

		createCurrentCommandField(content, c);
		createCommandList(content, c);
		createMoveButtons(content, c);
		createChangeCoordinatesOptions(content, c);
	}

	public void changeCoordinates(int x, int y, DriverCommand command) {
		int index = commandPartsList.getSelectedIndex();
		DriverCommand newCommand = commandCoordinateChangerVisitor.changeCoordinates(listModel.get(index), x, y);
		listModel.set(index, newCommand);
		commandManager.setCurrentCommand(Collections.list(listModel.elements()), "Custom command");
	}

	public void moveUp(DriverCommand command) {
		int index = commandPartsList.getSelectedIndex();
		if (index > 0) {
			listModel.set(index, listModel.get(index - 1));
			listModel.set(index - 1, command);
		}
		commandManager.setCurrentCommand(Collections.list(listModel.elements()), "Custom command");
		if (index > 0)
			commandPartsList.setSelectedIndex(index - 1);
	}

	public void moveDown(DriverCommand command) {
		int index = commandPartsList.getSelectedIndex();
		if (index < listModel.getSize() - 1) {
			listModel.set(index, listModel.get(index + 1));
			listModel.set(index + 1, command);
		}
		commandManager.setCurrentCommand(Collections.list(listModel.elements()), "Custom command");
		if (index < listModel.getSize() - 1)
			commandPartsList.setSelectedIndex(index + 1);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		this.setVisible(!this.isVisible());
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	public void updateCurrentCommandPartList() {
		DriverCommand command = commandManager.getCurrentCommand();
		listModel.removeAllElements();
		if (command != null) {
			command.accept(commandListingVisitor);
		}
	}

	private void createCurrentCommandField(Container content, GridBagConstraints c) {
		c.gridy = 0;
		c.weighty = 0.05;
		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		content.add(currentCommandField, c);
		updateCurrentCommandField();
	}

	private void createCommandList(Container content, GridBagConstraints c) {
		c.gridy = 1;
		c.weighty = 0.75;
		commandPartsList = new JList<>();
		listModel = new DefaultListModel<>();
		commandPartsList.setModel(listModel);
		commandListingVisitor = new CommandListingVisitor(listModel);
		content.add(commandPartsList, c);
		updateCurrentCommandPartList();
	}

	private void createMoveButtons(Container content, GridBagConstraints c) {
		c.weighty = 0.05;
		c.gridy = 2;
		moveUp = new JButton("Move command higher ");
		moveUp.addActionListener((ActionEvent e) -> this.moveUp(commandPartsList.getSelectedValue()));
		moveUp.setPreferredSize(new Dimension(300, 30));
		content.add(moveUp, c);

		c.gridy = 3;
		moveDown = new JButton("Move command lower ");
		moveDown.setPreferredSize(new Dimension(300, 30));
		moveDown.addActionListener((ActionEvent e) -> this.moveDown(commandPartsList.getSelectedValue()));
		content.add(moveDown, c);
	}

	private void createChangeCoordinatesOptions(Container content, GridBagConstraints c) {
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		Container container = new Container();
		GridLayout gridLayout = new GridLayout(1, 4);
		gridLayout.setHgap(25);
		container.setLayout(gridLayout);

		coordinatesX = new JTextArea("X coordinate ");
		coordinatesX.setEditable(false);
		container.add(coordinatesX);
		coordX = new JTextField("0");
		container.add(coordX);

		coordinatesY = new JTextArea("Y coordinate ");
		coordinatesY.setEditable(false);
		container.add(coordinatesY);
		coordY = new JTextField("0");
		container.add(coordY);
		content.add(container, c);

		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
		changeCoordinates = new JButton("Change coordinates ");
		changeCoordinates.setPreferredSize(new Dimension(300, 30));
		changeCoordinates.addActionListener(
				(ActionEvent e) -> this.changeCoordinates(Integer.parseInt(coordX.getText()), Integer.parseInt(coordY.getText()),
						commandPartsList.getSelectedValue()));
		content.add(changeCoordinates, c);
	}
}
