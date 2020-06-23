package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.line.Line2d;
import edu.kis.powp.jobs2d.features.CommandsFeature;

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

		updateViewToCurrentCommand();

		content.setVisible(true);
	}

	private void handleConfirmButton(ActionEvent actionEvent) {
	}

	private void handleButtonDownClickedEvent(ActionEvent actionEvent) {
		int index = commandList.getSelectedIndex();
		if (index < listModel.getSize() - 1) {
			swap(index, index + 1);
			commandList.setSelectedIndex(index + 1);
			commandList.ensureIndexIsVisible(index + 1);
		}
	}

	private void handleButtonUpClickedEvent(ActionEvent actionEvent) {
		int index = commandList.getSelectedIndex();
		if (index > 0) {
			swap(index, index - 1);
			commandList.setSelectedIndex(index - 1);
			commandList.ensureIndexIsVisible(index - 1);
		}
	}

	private void handleListSelectionEvent(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int index = commandList.getSelectedIndex();
			DriverCommand driverCommand = listModel.getElementAt(index);
			if(driverCommand instanceof HasCoordinates) {
				HasCoordinates command = (HasCoordinates) driverCommand;
				paramXInput.setText(String.valueOf(command.getX()));
				paramYInput.setText(String.valueOf(command.getY()));
			}

		}
	}

	private void swap(int a, int b) {
		DriverCommand aObject = listModel.getElementAt(a);
		DriverCommand bObject = listModel.getElementAt(b);
		listModel.set(a, bObject);
		listModel.set(b, aObject);
	}

	public void updateViewToCurrentCommand() {
		currentCommand = (ICompoundCommand) CommandsFeature.getDriverCommandManager().getCurrentCommand();
		int numberOfCommands = updateCommandList();
		numberOfCommandsValue.setText(String.valueOf(numberOfCommands));
		commandLengthValue.setText(String.valueOf(getCommandLength(currentCommand)));
		paramXInput.setText("");
		paramYInput.setText("");
		commandNameValue.setText(CommandsFeature.getDriverCommandManager().getCurrentCommandString());
	}

	private double getCommandLength(ICompoundCommand currentCommand) {
		if(currentCommand != null) {
			CommandLengthVisitor lengthVisitor = new CommandLengthVisitor();
			lengthVisitor.visit(currentCommand);
			return lengthVisitor.getLength();
		} else {
			return 0;
		}
	}

	private int updateCommandList() {
		listModel.clear();
		int numberOfCommands = 0;
		if(currentCommand != null) {
			Iterator<DriverCommand> iterator = currentCommand.iterator();
			while (iterator.hasNext()) {
				DriverCommand driverCommand = iterator.next();
				listModel.addElement(driverCommand);
				numberOfCommands++;
			}
		}

		return numberOfCommands;
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		this.setVisible(!this.isVisible());
	}
}
