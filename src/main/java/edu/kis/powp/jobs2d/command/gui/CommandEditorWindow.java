package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CustomCommandManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommandEditorWindow extends JFrame implements WindowComponent {

    private JTextArea currentCommandField;

    public CommandEditorWindow() {
        this.setTitle("Command Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        currentCommandField = new JTextArea("elo");
        currentCommandField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(currentCommandField, c);

        JButton btnClearCommand = new JButton("Save changes");
        btnClearCommand.addActionListener((ActionEvent e) -> this.saveCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearCommand, c);
    }

    private void saveCommand() {
        CustomCommandManager.setCustomStringCommand(currentCommandField.getText());
        System.out.println(currentCommandField.getText());
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }
}
