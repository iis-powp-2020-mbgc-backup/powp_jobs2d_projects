package edu.kis.powp.jobs2d.command.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private DriverCommandManager commandManager;

	private JTextArea currentCommandField;
	private JTextArea currentCommandAnalyzerField;
	private JLabel statisticsLabel;
	private String observerListString;
	private JTextArea observerListField;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(DriverCommandManager commandManager) {
		this.setTitle("Command Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		this.commandManager = commandManager;

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		content.add(observerListField, c);
		updateObserverListField();

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		content.add(currentCommandField, c);
		updateCurrentCommandField();

		statisticsLabel = new JLabel();
		content.add(statisticsLabel, c);
		statisticsLabel.setVisible(false);
		statisticsLabel.setText("Foreseen usage statistics of command: ");

		currentCommandAnalyzerField = new JTextArea("");
		currentCommandAnalyzerField.setEditable(false);
		content.add(currentCommandAnalyzerField, c);

		JButton btnClearCommand = new JButton("Clear command");
		JButton btnRunCommand = new JButton("Run command");

		btnClearCommand.addActionListener((ActionEvent e) -> {
			this.clearCommand();
			btnRunCommand.setEnabled(false);
		});

		content.add(btnClearCommand, c);

		btnRunCommand.setEnabled(false);
		btnRunCommand.addActionListener((ActionEvent e) -> this.runCommand());
		content.add(btnRunCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		JButton btnResetObservers = new JButton("Reset observers");
		btnResetObservers.setEnabled(false);

		btnResetObservers.addActionListener(e -> {
			this.resetObservers();
			btnClearObservers.setEnabled(true);
			btnResetObservers.setEnabled(false);
		});

		btnClearObservers.addActionListener((ActionEvent e) -> {
			this.deleteObservers();
			btnClearObservers.setEnabled(false);
			btnResetObservers.setEnabled(true);
		});

		content.add(btnClearObservers, c);
		content.add(btnResetObservers, c);

		this.commandManager.getChangePublisher().addSubscriber(() -> {
			btnRunCommand.setEnabled(true);
			statisticsLabel.setVisible(true);
			currentCommandAnalyzerField.setText(commandManager.getStatistics());
		});
	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		currentCommandAnalyzerField.setText("");
		statisticsLabel.setVisible(false);
		updateCurrentCommandField();
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	public void deleteObservers() {
		commandManager.deleteCurrentObservers();
		this.updateObserverListField();
	}

	/**
	 * Invokes method of <code>DriverCommandManager</code> that runs stored command, if set.
	 */
	public void runCommand(){
		commandManager.runCurrentCommand();
	}

	/**
	 * Restores observers from cache of <code>DriverCommandManager</code>.
	 */
	public void resetObservers(){
		commandManager.resetObservers();
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
