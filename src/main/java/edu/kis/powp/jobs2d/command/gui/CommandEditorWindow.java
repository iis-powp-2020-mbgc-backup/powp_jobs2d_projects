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
        this.setMinimumSize(new Dimension(300, 400));
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());
        content.setBackground(Color.WHITE);
        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.weightx = 1;

        setupWindow(content, c);
    }

    public void editCommand(int x, int y) {
        int index = commandPartsList.getSelectedIndex();
        CommandEditor editor = new CommandEditor(listModel.get(index));
        editor.edit(x,y);
        listModel.set(index, editor.getCommand());
        commandManager.setCurrentCommand(Collections.list(listModel.elements()), "Custom command");
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

    private void setupWindow(Container content, GridBagConstraints c) {
        c.gridy = 0;
        c.weighty = 0.05;
        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        content.add(currentCommandField, c);
        updateCurrentCommandField();

        c.gridy = 1;
        c.weighty = 0.5;
        commandPartsList = new JList<>();
        listModel = new DefaultListModel<>();
        commandPartsList.setModel(listModel);
        commandListingVisitor = new CommandListingVisitor(listModel);
        content.add(commandPartsList, c);
        updateCurrentCommandPartList();

        c.weighty = 0.05;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        Container container = new Container();
        GridLayout gridLayout = new GridLayout(1, 4);
        gridLayout.setHgap(25);
        container.setLayout(gridLayout);

        coordinatesX = new JTextArea("X:");
        coordinatesX.setEditable(false);
        container.add(coordinatesX);
        coordX = new JTextField("0");
        container.add(coordX);

        coordinatesY = new JTextArea("Y:");
        coordinatesY.setEditable(false);
        container.add(coordinatesY);
        coordY = new JTextField("0");
        container.add(coordY);
        content.add(container, c);

        c.gridy = 3;
        c.fill = GridBagConstraints.BOTH;
        changeCoordinates = new JButton("Edytuj ");
        changeCoordinates.setPreferredSize(new Dimension(300, 30));
        changeCoordinates.addActionListener(
                (ActionEvent e) -> this.editCommand(Integer.parseInt(coordX.getText()), Integer.parseInt(coordY.getText())));
        content.add(changeCoordinates, c);
    }

}