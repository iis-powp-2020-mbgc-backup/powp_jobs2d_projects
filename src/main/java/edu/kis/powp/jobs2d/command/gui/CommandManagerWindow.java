package edu.kis.powp.jobs2d.command.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.parsers.InputDataModel;
import edu.kis.powp.jobs2d.command.manager.parsers.JSONCommandParser;
import edu.kis.powp.jobs2d.features.CommandFactory;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CommandManagerWindow extends JFrame implements WindowComponent {
    private DriverCommandManager commandManager;
    Container content = this.getContentPane();
    private GridBagConstraints c;

    private JTextArea currentCommandField;
    private String observerListString;
    private JTextArea observerListField;
    private JTextArea InputCommandsTextArea;

    private JList commandFactoryList;
    private JComboBox lastCommands;
    private JButton btnAddSelectedCommand;
    private JButton btnDeleteSelectedCommand;
    private JButton btnSetSelectedCommand;
    private JButton btnRunSelectedCommand;
    private JSONCommandParser jsonCommandParser = new JSONCommandParser();
    private List<Subscriber> observers;
    private boolean isDeleted = false;
    /**
     *
     */
    private static final long serialVersionUID = 9204679248304669948L;

    public CommandManagerWindow(DriverCommandManager commandManager) {
        this.setTitle("Command Manager");
        this.setSize(600, 800);
        content = this.getContentPane();
        content.setLayout(new GridBagLayout());
        CommandFactory commandFactory = new CommandFactory();
        this.commandManager = commandManager;
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 2;
        observerListField = new JTextArea("");
        observerListField.setEditable(false);
        addToWindow(observerListField);
        updateObserverListField();
        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        addToWindow(currentCommandField);
        updateCurrentCommandField();
        InputCommandsTextArea = new JTextArea("");
        InputCommandsTextArea.setEditable(true);
        InputCommandsTextArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        InputCommandsTextArea.setLineWrap(true);
        JScrollPane InputCommandsField = new JScrollPane(InputCommandsTextArea);
        addToWindow(InputCommandsField);
        JButton jsonLoadCommands = new JButton("Load commands");
        jsonLoadCommands.addActionListener((ActionEvent e) -> this.loadCommandsFromJSON(InputCommandsTextArea.getText().trim()));
        addToWindow(jsonLoadCommands);
        JButton btnClearCommand = new JButton("Clear command");
        btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
        addToWindow(btnClearCommand);
        JButton btnRunCommand = new JButton("Run command");
        btnRunCommand.addActionListener((ActionEvent e) -> this.runCommand());
        addToWindow(btnRunCommand);
        JButton btnHandleObservers = new JButton("Delete observers");
        btnHandleObservers.addActionListener((ActionEvent e) -> this.handleObservers(btnHandleObservers));
        addToWindow(btnHandleObservers);
        JLabel factoryLabel = new JLabel("Command Factory");
        addToWindow(factoryLabel);
        DefaultListModel commandFactoryModel = new DefaultListModel();
        commandFactoryList = new JList(commandFactoryModel);
        JScrollPane scrollPane = new JScrollPane(commandFactoryList);
        commandFactoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        addToWindow(scrollPane);
        JLabel lastCommandsLabel = new JLabel("Last commands");
        addToWindow(lastCommandsLabel);
        Vector<DriverCommand> commands = new Vector<>();
        lastCommands = new JComboBox(commands);
        this.commandManager.getChangePublisher().addSubscriber(() -> {
//            String currentCommand = commandManager.getCurrentCommandString().replace("CompoundCommand name: ", "");
            DriverCommand currentCommand = commandManager.getCurrentCommand();
            commands.add(currentCommand);

            lastCommands.setSelectedItem(currentCommand);
        });
        addToWindow(lastCommands);
        btnAddSelectedCommand = new JButton("Add command to factory");
        btnAddSelectedCommand.addActionListener((ActionEvent e) -> {
            try {
                DriverCommand selectedCommand = (DriverCommand)lastCommands.getSelectedItem();
                assert selectedCommand != null;
                if(!commandFactoryModel.contains(selectedCommand))
                    commandFactoryModel.add(commandFactoryModel.getSize(), selectedCommand);
                commandFactory.addCommand(selectedCommand);
            } catch (CloneNotSupportedException excp) {
                excp.printStackTrace();
            }
        });
        addToWindow(btnAddSelectedCommand);

        btnDeleteSelectedCommand = new JButton("Delete command from factory");
        btnDeleteSelectedCommand.addActionListener((ActionEvent e) ->
        {
            commandFactory.removeCommand(commandFactoryList.getSelectedValue().toString());
            commandFactoryModel.remove(commandFactoryList.getSelectedIndex());
        });
        addToWindow(btnDeleteSelectedCommand);

        btnSetSelectedCommand = new JButton("Set selected command");
        btnSetSelectedCommand.addActionListener((ActionEvent e) ->
                {
                    try {
                        if (commandFactoryList.getSelectedIndex() != -1) {
                            DriverCommand command = commandFactory.getCommand(commandFactoryList.getSelectedValue().toString());
                            commandManager.setCurrentCommand(command);
                        }
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                }
        );
        addToWindow(btnSetSelectedCommand);
        btnRunSelectedCommand = new JButton("Run selected command");
        btnRunSelectedCommand.addActionListener((ActionEvent e) ->
                {
                    commandManager.runCurrentCommand();
                }
        );
        addToWindow(btnRunSelectedCommand);
    }

    public void handleObservers(JButton button) {
        if (this.isDeleted) {
            this.resetObservers();
            button.setText("Delete observers");
        } else {
            this.deleteObservers();
            button.setText("Reset observers");
        }
        this.updateObserverListField();
        this.isDeleted = !this.isDeleted;
    }

    private void addToWindow(JComponent component) {
        content.add(component, c);
    }

    private void loadCommandsFromJSON(String jsonInput) {
        InputDataModel inputDataModel = jsonCommandParser.parse(jsonInput);
        commandManager.setCurrentCommand(
                inputDataModel.getDriverCommand(),
                inputDataModel.getDriverCommandName()
        );
    }

    private void clearCommand() {
        commandManager.clearCurrentCommand();
        updateCurrentCommandField();
    }

    private void runCommand() {
        commandManager.runCurrentCommand();
        updateCurrentCommandField();
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
    }

    public void deleteObservers() {
        this.observers = new ArrayList<>(this.commandManager.getChangePublisher().getSubscribers());
        commandManager.getChangePublisher().clearObservers();
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

    public void resetObservers() {
        if (this.observers != null && this.observers.size() > 0) {
            this.commandManager.getChangePublisher().clearObservers();
            this.addObservers(this.observers);
        }
    }

    private void addObservers(List<Subscriber> observers) {
        if (observers != null) {
            for (Subscriber observer : observers) {
                this.commandManager.getChangePublisher().addSubscriber(observer);
            }
        }
    }

    private void addSelectedCommand() {

    }

    private void deleteSelectedCommand() {
    }

    private void setSelectedCommand() {
    }

    private void runSelectedCommand() {
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