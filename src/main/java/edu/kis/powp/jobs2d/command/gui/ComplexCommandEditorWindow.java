package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.complexCommandEditor.ComplexCommandEditor;
import edu.kis.powp.jobs2d.command.line.Line2d;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class ComplexCommandEditorWindow extends JFrame implements WindowComponent {

	private final JLabel commandNameValue;
	private final JTextField paramXInput;
	private final JTextField paramYInput;
	private final JList<DriverCommand> commandList;
	private final DefaultListModel<DriverCommand> listModel;
	private final JLabel numberOfCommandsValue;
	private final JLabel commandLengthValue;

	private ComplexCommandEditor complexCommandEditor;
	private ICompoundCommand currentCommand;

	public ComplexCommandEditorWindow() {

		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());
		this.setTitle("Complex command editor");
		this.setSize(640, 480);


		// Layout
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(80, 120));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		content.add(topPanel, BorderLayout.PAGE_START);

		JPanel topNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(topNamePanel);

		JPanel topStatisticsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(topStatisticsPanel);

		JPanel topStatisticsNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topStatisticsPanel.add(topStatisticsNumberPanel);

		JPanel topStatisticsLengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topStatisticsPanel.add(topStatisticsLengthPanel);

		JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		content.add(mainPanel, BorderLayout.CENTER);

		JScrollPane mainLeftPanel = new JScrollPane();
		mainPanel.add(mainLeftPanel);

		JPanel mainRightPanel = new JPanel();
		mainRightPanel.setLayout(new BoxLayout(mainRightPanel, BoxLayout.Y_AXIS));
		mainPanel.add(mainRightPanel);

		JPanel mainRightParametersPanel = new JPanel();
		mainRightPanel.add(mainRightParametersPanel);

		JPanel mainRightOrderPanel = new JPanel();
		mainRightPanel.add(mainRightOrderPanel);

		JPanel mainRightBottomPanel = new JPanel();
		mainRightPanel.add(mainRightBottomPanel);

		// Controls
		JLabel commandName = new JLabel("Current command: ");
		topNamePanel.add(commandName);
		commandNameValue = new JLabel("no command loaded");
		topNamePanel.add(commandNameValue);

		JLabel numberOfCommands = new JLabel("Number of commands: ");
		topStatisticsNumberPanel.add(numberOfCommands);
		numberOfCommandsValue = new JLabel("N/A");
		topStatisticsNumberPanel.add(numberOfCommandsValue);

		JLabel commandLength = new JLabel("Total length: ");
		topStatisticsLengthPanel.add(commandLength);
		commandLengthValue = new JLabel("N/A");
		topStatisticsLengthPanel.add(commandLengthValue);

		listModel = new DefaultListModel<>();
		commandList = new JList<>(listModel);
		commandList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mainLeftPanel.setViewportView(commandList);
		commandList.addListSelectionListener(this::handleListSelectionEvent);

		JLabel paramXLabel = new JLabel("X: ");
		mainRightParametersPanel.add(paramXLabel);
		paramXInput = new JTextField(5);
		mainRightParametersPanel.add(paramXInput);

		JLabel paramYLabel = new JLabel("Y: ");
		mainRightParametersPanel.add(paramYLabel);
		paramYInput = new JTextField(5);
		mainRightParametersPanel.add(paramYInput);

		JButton changeOrderUpButton = new JButton("Up");
		changeOrderUpButton.addActionListener(this::handleButtonUpClickedEvent);
		mainRightOrderPanel.add(changeOrderUpButton);

		JButton changeOrderDownButton = new JButton("Down");
		changeOrderDownButton.addActionListener(this::handleButtonDownClickedEvent);
		mainRightOrderPanel.add(changeOrderDownButton);

		JButton confirmButton = new JButton("Apply changes");
		confirmButton.addActionListener(this::handleConfirmButton);
		mainRightBottomPanel.add(confirmButton);

		JButton newOperateToCommandButton = new JButton("New operate to command");
		newOperateToCommandButton.addActionListener(this::addOperateToCommand);
		mainRightBottomPanel.add(newOperateToCommandButton);

		JButton newSetToCommandButton = new JButton("New set to command");
		newSetToCommandButton.addActionListener(this::addNewSetToCommand);
		mainRightBottomPanel.add(newSetToCommandButton);

		JButton deleteCommandButton = new JButton("Delete");
		deleteCommandButton.addActionListener(this::deleteCommand);
		mainRightBottomPanel.add(deleteCommandButton);

		updateViewToCurrentCommand();

		content.setVisible(true);
	}

	private void handleConfirmButton(ActionEvent actionEvent) {
		java.util.List<DriverCommand> driverCommandList = new ArrayList<>();
		if(complexCommandEditor != null) {
			complexCommandEditor.getEditedComplexCommand().iterator().forEachRemaining(command -> driverCommandList.add(command));
		}
		CommandsFeature.getDriverCommandManager().setCurrentCommand(driverCommandList, "Command made in Complex Command Editor");
	}

	private void deleteCommand(ActionEvent actionEvent) {
		int index = commandList.getSelectedIndex();
		complexCommandEditor.deleteCommand(commandList.getSelectedIndex());
		updateJList(complexCommandEditor.getEditedComplexCommand());
		updateCommandStatistics(complexCommandEditor.getEditedComplexCommand());
		commandList.setSelectedIndex(index);
	}


	private void addNewSetToCommand(ActionEvent actionEvent) {
		SetPositionCommand setPositionCommand = new SetPositionCommand(Integer.parseInt(paramXInput.getText()),
			Integer.parseInt(paramYInput.getText()));

		complexCommandEditor.addCommand(setPositionCommand);
		updateJList(complexCommandEditor.getEditedComplexCommand());
		updateCommandStatistics(complexCommandEditor.getEditedComplexCommand());
		commandList.setSelectedIndex(listModel.size()-1);
	}

	private void addOperateToCommand(ActionEvent actionEvent) {
		OperateToCommand operateToCommand = new OperateToCommand(Integer.parseInt(paramXInput.getText()),
			Integer.parseInt(paramYInput.getText()));

		complexCommandEditor.addCommand(operateToCommand);
		updateJList(complexCommandEditor.getEditedComplexCommand());
		updateCommandStatistics(complexCommandEditor.getEditedComplexCommand());
		commandList.setSelectedIndex(listModel.size()-1);


	}



	private void handleButtonDownClickedEvent(ActionEvent actionEvent) {
		complexCommandEditor.moveCommandDown(commandList.getSelectedIndex());
		int index = commandList.getSelectedIndex();
		updateJList(complexCommandEditor.getEditedComplexCommand());
		commandList.setSelectedIndex(index + 1);
	}

	private void updateJList(ICompoundCommand editedComplexCommand) {
		listModel.clear();
		if(editedComplexCommand != null) {
			editedComplexCommand.iterator().forEachRemaining(command -> listModel.addElement(command));
		}

	}


	private void handleButtonUpClickedEvent(ActionEvent actionEvent) {
		complexCommandEditor.moveCommandUp(commandList.getSelectedIndex());
		int index = commandList.getSelectedIndex();
		updateJList(complexCommandEditor.getEditedComplexCommand());
		commandList.setSelectedIndex(index - 1);
	}

	private void handleListSelectionEvent(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int index = commandList.getSelectedIndex();
			if(index > -1) {
				DriverCommand driverCommand = listModel.getElementAt(index);
				if (driverCommand instanceof HasCoordinates) {
					HasCoordinates command = (HasCoordinates) driverCommand;
					paramXInput.setText(String.valueOf(command.getX()));
					paramYInput.setText(String.valueOf(command.getY()));
				}
			}
		}
	}


	public void updateViewToCurrentCommand() {
		currentCommand = (ICompoundCommand) CommandsFeature.getDriverCommandManager().getCurrentCommand();

		if(currentCommand!= null) {
			updateCommandStatistics(currentCommand);
			complexCommandEditor = new ComplexCommandEditor(currentCommand);
			updateJList(complexCommandEditor.getEditedComplexCommand());
			commandNameValue.setText(CommandsFeature.getDriverCommandManager().getCurrentCommandString());
		}

		paramXInput.setText("");
		paramYInput.setText("");
	}

	private void updateCommandStatistics(ICompoundCommand iCompoundCommand) {
		CommandCounterVisitor commandCounterVisitor = new CommandCounterVisitor();
		CommandLengthVisitor commandLengthVisitor = new CommandLengthVisitor();
		commandCounterVisitor.visit(iCompoundCommand);
		commandLengthVisitor.visit(iCompoundCommand);

		numberOfCommandsValue.setText(String.valueOf(commandCounterVisitor.getAllCommandsCounter()));
		commandLengthValue.setText(String.valueOf(commandLengthVisitor.getLength()));
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		this.setVisible(!this.isVisible());
	}
}
