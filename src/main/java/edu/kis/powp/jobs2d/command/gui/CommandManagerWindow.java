package edu.kis.powp.jobs2d.command.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Choice;
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
import javax.swing.text.LayoutQueue;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandHistoryController;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import edu.kis.powp.jobs2d.events.SelectHistoryListOptionListener;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.command.manager.parsing.JsonParser;
import edu.kis.powp.jobs2d.features.CommandFactory;
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
	private Parser parser;

	Container content = this.getContentPane();
	GridBagConstraints c = new GridBagConstraints();
	Choice catalog;
	CommandFactory commandFactory = new CommandFactory();

	/**
	 *
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(DriverCommandManager commandManager) {
		this.setTitle("Command Manager");
		this.setSize(400, 400);
		content.setLayout(new GridBagLayout());

		this.commandManager = commandManager;
		commandFactory.setCommandManagerPublisher(commandManager);

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		c.gridy = 1;
		content.add(observerListField, c);
		updateObserverListField();

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		c.gridy = 2;
		content.add(currentCommandField, c);
		updateCurrentCommandField();

		parser = new JsonParser();

		Container container = getImportContainer();
		c.gridy = 3;
		content.add(container, c);

		JButton btnExportCommand = new JButton("Export commands ");
		btnExportCommand.addActionListener((ActionEvent e) -> this.exportCommands());
		c.gridy = 4;
		content.add(btnExportCommand, c);

		JButton btnClearCommand = new JButton("Clear command");
		btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
		c.gridy = 5;
		content.add(btnClearCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers(btnClearObservers));
		c.gridy = 6;
		content.add(btnClearObservers, c);

		JButton btnRunCommand = new JButton("Run command");
		btnRunCommand.addActionListener((ActionEvent e) -> this.runCommand());
		c.gridy = 7;
		content.add(btnRunCommand, c);

		DefaultListModel model = new DefaultListModel();
		CommandHistoryController.setListModel(model);
		JList commandHistoryList = new JList(model);
		commandHistoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		commandHistoryList
				.addListSelectionListener(new SelectHistoryListOptionListener(commandManager, commandHistoryList));

		content.add(new JScrollPane(commandHistoryList), c);
		setupCommandCatalog();
	}

	private void setupCommandCatalog() {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(4, 2);
		panel.setLayout(layout);
		JLabel label = new JLabel("Command catalog");
		panel.add(label, c);

		catalog = new Choice();
		panel.add(catalog, c);

		JButton btnCatalogClear = new JButton("Clear catalog");
		btnCatalogClear.addActionListener((e) -> {
			commandFactory.clearCommands();
			catalog.removeAll();
		});
		panel.add(btnCatalogClear);

		JButton btnCatalogSet = new JButton("Set command");
		btnCatalogSet.addActionListener((e) -> {
			DriverCommand current = commandFactory.getCommand(catalog.getSelectedItem());
			List<DriverCommand> commandList = new ArrayList<>();
			commandList.add(current);
			commandManager.setCurrentCommand(commandList, catalog.getSelectedItem());
		});
		panel.add(btnCatalogSet);

		this.commandManager.getChangePublisher().addSubscriber(() -> {
			catalog.removeAll();
			commandFactory.getCommandsNames().forEach(name -> {
				catalog.add(name);
			});
			catalog.select(commandManager.getCurrentCommandString());
		});
		c.gridy = 8;
		content.add(panel, c);
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

	private void exportCommands() {
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
