package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.CommandCoordinatesVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.transformations.TransformationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommandTransformationWindow extends JFrame implements WindowComponent {

    private DriverCommandManager commandManager;
    private TransformationManager transformationManager;

    public CommandTransformationWindow(DriverCommandManager commandManager, TransformationManager transformationManager) {
        this.setTitle("Command Transformation Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;
        this.transformationManager = transformationManager;

        GridBagConstraints c = new GridBagConstraints();

        JButton btnLoadCommand = new JButton("Transform command");
        btnLoadCommand.addActionListener((ActionEvent e) -> this.moveCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadCommand, c);
    }

    private void moveCommand() {
        CommandCoordinatesVisitor visitor = new CommandCoordinatesVisitor();
        visitor.visit(commandManager.getCurrentCommand());
        DriverCommand d = transformationManager.moveCommand(visitor, 50, 0);
        commandManager.setCurrentCommand(d);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
