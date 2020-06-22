package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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

    public CommandEditorWindow(DriverCommandManager commandManager) {
        this.setTitle("Command Editor");
        this.setMinimumSize(new Dimension(500, 600));
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());
        content.setBackground(Color.WHITE);
        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();
        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.05;
        c.weightx = 1;

        content.add(currentCommandField, c);
        updateCurrentCommandField();

        commandPartsList = new JList<>();
        commandPartsList.setModel(new DefaultListModel<>());
        c.gridy = 1;
        c.weighty = 0.75;
        content.add(commandPartsList, c);
        updateCurrentCommandPartList();

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

        c.gridy =4;
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
        changeCoordinates.addActionListener((ActionEvent e) -> this.changeCoordinates(Integer.parseInt(coordX.getText()), Integer.parseInt(coordY.getText()),commandPartsList.getSelectedValue()));
        content.add(changeCoordinates, c);
    }

    public void changeCoordinates(int x, int y, DriverCommand command) {
        if(commandManager.getCurrentCommand() instanceof ICompoundCommand) {
            if (command instanceof SetPositionCommand) {
                commandManager.setCurrentCommand(
                        ((ICompoundCommand) commandManager.getCurrentCommand()).changeCoordinates(command, new SetPositionCommand(x, y)));
            } else if (command instanceof OperateToCommand) {
                commandManager.setCurrentCommand(
                        ((ICompoundCommand) commandManager.getCurrentCommand()).changeCoordinates(command, new OperateToCommand(x, y)));
            }
        }
    }

    public void moveUp(DriverCommand command) {
        if (commandManager.getCurrentCommand() instanceof ICompoundCommand) {
            commandManager.setCurrentCommand(((ICompoundCommand) commandManager.getCurrentCommand()).moveUpCommand(command));
        }
        updateCurrentCommandField();
    }

    public void moveDown(DriverCommand command) {
        if (commandManager.getCurrentCommand() instanceof ICompoundCommand) {
            commandManager.setCurrentCommand(((ICompoundCommand) commandManager.getCurrentCommand()).moveDownCommand(command));
        }
        updateCurrentCommandField();
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
    }

    public void updateCurrentCommandPartList() {
        DriverCommand command = commandManager.getCurrentCommand();
        DefaultListModel<DriverCommand> listModel = (DefaultListModel<DriverCommand>) commandPartsList.getModel();
        listModel.removeAllElements();
        addCommandsToList(listModel, command);
    }

    private void addCommandsToList(DefaultListModel<DriverCommand> listModel, DriverCommand command) {
        if (command instanceof ICompoundCommand) {
            ((ICompoundCommand) command).iterator().forEachRemaining(cmd -> {
                if (cmd instanceof ICompoundCommand) {
                    addCommandsToList(listModel, cmd);
                } else {
                    listModel.addElement(cmd);
                }
            });
        }
    }
}
