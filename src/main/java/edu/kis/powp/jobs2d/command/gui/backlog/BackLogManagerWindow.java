package edu.kis.powp.jobs2d.command.gui.backlog;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class BackLogManagerWindow extends JFrame implements WindowComponent {

    private DriverCommandManager commandManager;
    private JTextArea commandListField;


    public BackLogManagerWindow(DriverCommandManager commandManager) {
        this.setTitle("BackLog Viewer");
        this.setSize(600, 500);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;

        commandListField = new JTextArea("");
        commandListField.setEditable(false);
        content.add(commandListField, c);
        updateLog();

    }

    private void updateLog() {
        /**
         *  Here we will add some backlog infos about used functions
         */
        commandListField.setText("History of commands: \n");
    }

    public void updateCommandListField() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        commandListField.append("Used command ("+java.time.LocalTime.now().format(dtf) + "): " + commandManager.getCurrentCommandString() + "\n");
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
