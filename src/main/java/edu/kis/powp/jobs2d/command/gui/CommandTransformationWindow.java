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
    private JTextArea shiftXValueField;
    private JTextArea shiftYValueField;
    private JTextArea scaleValueField;
    private JTextArea angleValueField;


    public CommandTransformationWindow(DriverCommandManager commandManager, TransformationManager transformationManager) {
        this.setTitle("Command Transformation Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;
        this.transformationManager = transformationManager;

        GridBagConstraints c = new GridBagConstraints();

        shiftXValueField = new JTextArea("Enter X here");
        shiftXValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(shiftXValueField, c);

        shiftYValueField = new JTextArea("Enter Y here");
        shiftYValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(shiftYValueField, c);

        JButton btnLoadTransformCommand = new JButton("Transform command");
        btnLoadTransformCommand.addActionListener((ActionEvent e) -> this.moveCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadTransformCommand, c);

        scaleValueField = new JTextArea("Enter scale value");
        scaleValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(scaleValueField, c);

        JButton btnLoadScaleCommand = new JButton("Scale command");
        btnLoadScaleCommand.addActionListener((ActionEvent e) -> this.scaleCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadScaleCommand, c);

        angleValueField = new JTextArea("Enter angle");
        angleValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(angleValueField, c);

        JButton btnLoadRotateCommand = new JButton("Rotate command");
        btnLoadRotateCommand.addActionListener((ActionEvent e) -> this.rotateCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadRotateCommand, c);

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

    }

    private void moveCommand() {
        CommandCoordinatesVisitor visitor = new CommandCoordinatesVisitor();
        visitor.visit(commandManager.getCurrentCommand());
        DriverCommand d = transformationManager.moveCommand(visitor, getFieldValue(shiftXValueField.getText()), getFieldValue(shiftYValueField.getText()));
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
        DriverCommand d = transformationManager.scaleCommand(visitor,getFieldValue(scaleValueField.getText()));
        commandManager.setCurrentCommand(d);
    }

    private void rotateCommand() {
        CommandCoordinatesVisitor visitor = new CommandCoordinatesVisitor();
        visitor.visit(commandManager.getCurrentCommand());
        DriverCommand d = transformationManager.rotateCommand(visitor,getFieldValue(angleValueField.getText()));
        commandManager.setCurrentCommand(d);
    }
    
    private Integer getFieldValue(String input) {
        Integer result = null;
        System.out.println(Integer.valueOf(input));
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException nfe) {
            System.out.println("invalid input");
        }
        return result;
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
