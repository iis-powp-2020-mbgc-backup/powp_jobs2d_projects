package edu.kis.powp.jobs2d.command.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.filechooser.FileSystemView;

import java.awt.event.ActionListener;
import java.awt.Component;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandHistoryController;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import edu.kis.powp.jobs2d.events.SelectHistoryListOptionListener;
import edu.kis.powp.jobs2d.features.CommandFactory;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.command.manager.parsing.JsonParser;
import edu.kis.powp.jobs2d.command.manager.parsing.Parser;

import edu.kis.powp.observer.Subscriber;

public class CommandManagerWindow extends JFrame implements WindowComponent {
	private List<Subscriber> observerList;
	private boolean observersDeleted = false;
	private DriverCommandManager commandManager;

	private JTextArea currentCommandField;

	private String observerListString;
	private JTextArea observerListField;

	private JTextField txtFieldToImport;
	private Parser parser = new JsonParser();

	private DefaultListModel factoryModel;
	GridBagConstraints constrains = new GridBagConstraints();

	/**
	 *
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(DriverCommandManager commandManager) {
		this.setTitle("Command Manager");
		this.setSize(500, 700);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		this.commandManager = commandManager;
		constrains.fill = GridBagConstraints.BOTH;
		constrains.weightx = 1;
		constrains.gridx = 0;
		constrains.weighty = 0;

		List<Component> components = new ArrayList<>();

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		updateObserverListField();
		components.add(observerListField);

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		updateCurrentCommandField();
		components.add(currentCommandField);
		
		components.add(getImportContainer());
		components.add(createActionButton("Export commands ", (ActionEvent e) -> this.exportCommands()));
		components.add(createActionButton("Clear command", (ActionEvent e) -> this.clearCommand()));

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers(btnClearObservers));
		components.add(btnClearObservers);
		components.add(createActionButton("Run command", (ActionEvent e) -> this.runCommand()));

		DefaultListModel model = new DefaultListModel();
		CommandHistoryController.setListModel(model);
		JList commandHistoryList = new JList(model);
		commandHistoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		commandHistoryList
				.addListSelectionListener(new SelectHistoryListOptionListener(commandManager, commandHistoryList));
		components.add(commandHistoryList);

		for (Component component : components) {
			content.add(component, constrains);
			constrains.weighty++;
		}

		setupCommandFactoryView(content);
	}

	private void setupCommandFactoryView(Container content) {
		List<Component> components = new ArrayList<>();
		
		factoryModel = new DefaultListModel();
		JList commandFactoryList = new JList(factoryModel);
		JScrollPane scrollPane = new JScrollPane(commandFactoryList);
		components.add(scrollPane);
		for (String item : (CommandFactory.getInstance()).getCommandNames()) {
			addCommandToFactoryList(item);
		}
		components.add(createActionButton("Add current command to factory", (ActionEvent event) -> {
			try {
				CommandFactory.getInstance().addCommand(commandManager.getCurrentCommand());
				addCommandToFactoryList(commandManager.getCurrentCommandString().replace("CompoundCommand name: ", ""));
			} catch (CloneNotSupportedException exception) {
				exception.printStackTrace();
			}
		}));
		components.add(createActionButton("Set current command", (ActionEvent event) -> {
			if (commandFactoryList.getSelectedIndex() != -1) {
				DriverCommand command = CommandFactory.getInstance().getCommand((String) commandFactoryList.getSelectedValue());
				commandManager.setCurrentCommand(command);
			}
		}));
		components.add(createActionButton("Run current command", (ActionEvent event) -> {
			commandManager.runCurrentCommand();
		}));
		components.add(createActionButton("Remove command from factory", (ActionEvent event) -> {
			if (commandFactoryList.getSelectedIndex() != -1) {
				CommandFactory.getInstance().removeCommand((String) commandFactoryList.getSelectedValue());
				factoryModel.remove(commandFactoryList.getSelectedIndex());
			}
		}));

		for (Component component : components) {
			content.add(component, constrains);
			constrains.weighty++;
		}
	}

	private JButton createActionButton(String text, ActionListener action) {
		JButton result = new JButton(text);
		result.addActionListener(action);
		return result;
	}

	public void addCommandToFactoryList(String commandName) {
        if (!factoryModel.contains(commandName)) {
            factoryModel.add(factoryModel.getSize(), commandName);
        }
	}

	private Container getImportContainer() {
		Container container = new Container();
		GridLayout gridLayout = new GridLayout(1, 2);
		container.setLayout(gridLayout);

		txtFieldToImport = new JTextField();
		container.add(txtFieldToImport);

		JButton btnImportCommand = new JButton("Import commands");
		btnImportCommand.addActionListener((ActionEvent e) -> this.importCommands());
		container.add(btnImportCommand);
		return container;
	}

	private void importCommands() {
		commandManager.setCurrentCommand(parser.parseFromString(txtFieldToImport.getText()));
	}

	private void exportCommands()   {
		JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")),
				FileSystemView.getFileSystemView());
		fileChooser.setDialogTitle("Specify a file to save");
		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			BufferedWriter bufferedWriter;
			try {
				bufferedWriter = new BufferedWriter(new FileWriter(fileToSave));
				String commandAsString = parser.parseToString(commandManager.getCurrentCommand());
				bufferedWriter.write(commandAsString);
				bufferedWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}

		}
	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		updateCurrentCommandField();
	}

	private void runCommand() {
		commandManager.runCurrentCommand();
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	public void deleteObservers(JButton deleteButton) {
		if (observersDeleted) {
			resetObservers(deleteButton);
		} else {
			this.observerList = this.commandManager.getChangePublisher().getSubscribers();
			commandManager.getChangePublisher().clearObservers();
			this.updateObserverListField();
			observersDeleted = true;
			deleteButton.setText("Reset observers");
		}

	}

	public void resetObservers(JButton deleteButton) {
		commandManager.getChangePublisher().clearObservers();
		observersDeleted = false;
		deleteButton.setText("Delete observers");

		if (observerList != null) {
			for (Subscriber subscriber : observerList) {
				this.commandManager.getChangePublisher().addSubscriber(subscriber);
			}
		}
		this.updateObserverListField();

	}

	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
	}



	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}
