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

        JButton btnLoadTransformCommand = new JButton("Transform command");
        btnLoadTransformCommand.addActionListener((ActionEvent e) -> this.moveCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadTransformCommand, c);

        JButton btnLoadhorizontalCommand = new JButton("Flip horizontal command");
        btnLoadhorizontalCommand.addActionListener((ActionEvent e) -> this.flipHorizontalCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadhorizontalCommand, c);

        JButton btnLoadVerticalCommand = new JButton("Flip vertical command");
        btnLoadVerticalCommand.addActionListener((ActionEvent e) -> this.flipVerticalCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadVerticalCommand, c);

        JButton btnLoadScaleCommand = new JButton("Scale command");
        btnLoadScaleCommand.addActionListener((ActionEvent e) -> this.scaleCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadScaleCommand, c);
    }

    private void moveCommand() {
        CommandCoordinatesVisitor visitor = new CommandCoordinatesVisitor();
        visitor.visit(commandManager.getCurrentCommand());
        DriverCommand d = transformationManager.moveCommand(visitor, 50, 0);
        commandManager.setCurrentCommand(d);
    }

    private void flipHorizontalCommand() {
        CommandCoordinatesVisitor visitor = new CommandCoordinatesVisitor();
        visitor.visit(commandManager.getCurrentCommand());
        DriverCommand d = transformationManager.flipHorizontalCommand(visitor);
        commandManager.setCurrentCommand(d);
    }

    private void flipVerticalCommand() {
        CommandCoordinatesVisitor visitor = new CommandCoordinatesVisitor();
        visitor.visit(commandManager.getCurrentCommand());
        DriverCommand d = transformationManager.flipVerticalCommand(visitor);
        commandManager.setCurrentCommand(d);
    }

    private void scaleCommand() {
        CommandCoordinatesVisitor visitor = new CommandCoordinatesVisitor();
        visitor.visit(commandManager.getCurrentCommand());
        DriverCommand d = transformationManager.scaleCommand(visitor,2);
        commandManager.setCurrentCommand(d);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
