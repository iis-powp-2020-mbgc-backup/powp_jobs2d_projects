package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.CommandEditor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommandEditorWindow extends JFrame implements WindowComponent {

    private DriverCommandManager commandManager;

    private JTextArea currentCommandField;
    private JList<DriverCommand> commandPartsList;
    private JTextField coordX;
    private JTextField coordY;
    private JTextArea coordinatesX;
    private JTextArea coordinatesY;
    private JButton editButton;

    private static final long serialVersionUID = 9204679248304669950L;

    public CommandEditorWindow(DriverCommandManager commandManager) {
        this.setTitle("Command Editor");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();

        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(currentCommandField, c);
        updateCurrentCommandField();

        commandPartsList = new JList<>();
        commandPartsList.setModel(new DefaultListModel<>());
        c.gridy = 1;
        c.weighty = 0.05;
        content.add(commandPartsList, c);
        updateCurrentCommandCompoundsList();

        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        Container container = new Container();
        GridLayout gridLayout = new GridLayout(1, 4);
        gridLayout.setHgap(25);
        container.setLayout(gridLayout);

        coordinatesX = new JTextArea("X: ");
        coordinatesX.setEditable(false);
        container.add(coordinatesX);
        coordX = new JTextField("0");
        container.add(coordX);

        coordinatesY = new JTextArea("Y: ");
        coordinatesY.setEditable(false);
        container.add(coordinatesY);
        coordY = new JTextField("0");
        container.add(coordY);
        content.add(container, c);

        c.gridy = 5;
        editButton = new JButton("Execute edit");
        editButton.addActionListener((ActionEvent e) -> this.edit(commandPartsList.getSelectedValue()));
        editButton.setPreferredSize(new Dimension(300, 30));
        content.add(editButton, c);

    }

    public void edit(DriverCommand command) {
        System.out.println(command);
        CommandEditor editor = new CommandEditor(command);
        editor.edit(Integer.parseInt(coordX.getText()), Integer.parseInt(coordY.getText()));
        commandManager.setCurrentCommand(editor.getCommand());
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
    }

    public void updateCurrentCommandCompoundsList() {
        DriverCommand command = commandManager.getCurrentCommand();
        DefaultListModel<DriverCommand> listModel = (DefaultListModel<DriverCommand>) commandPartsList.getModel();
        listModel.removeAllElements();
        addCommandsToCompoundsList(listModel, command);
    }

    private void addCommandsToCompoundsList(DefaultListModel<DriverCommand> listModel, DriverCommand command) {
        if (command instanceof ICompoundCommand) {
            ((ICompoundCommand) command).iterator().forEachRemaining(cmd -> {
                if (cmd instanceof ICompoundCommand) {
                    addCommandsToCompoundsList(listModel, cmd);
                } else {
                    listModel.addElement(cmd);
                }
            });
        }
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

}
