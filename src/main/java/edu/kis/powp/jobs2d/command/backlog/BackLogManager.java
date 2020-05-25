package edu.kis.powp.jobs2d.command.backlog;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import java.awt.*;

public class BackLogManager extends JFrame implements WindowComponent {

    private DriverCommandManager commandManager;
    private JTextArea currentCommandField;
    private String observerListString;
    private JTextArea observerListField;


    public BackLogManager(DriverCommandManager commandManager) {
        this.setTitle("BackLog Viewer");
        this.setSize(800, 400);
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
        updateLog();

    }

    private void updateLog() {
        /**
         *  Here we will add some backlog infos about used functions
         */
        observerListString = "Nothing to show";
        observerListField.setText(observerListString);
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
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
