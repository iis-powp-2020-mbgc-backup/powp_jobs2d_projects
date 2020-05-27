package edu.kis.powp.jobs2d.command.historyComponent;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class HistoryManagerWindow extends JFrame implements WindowComponent {

    private DriverCommandManager commandManager;
    private JTextArea commandHistoryContent;
    private JFrame frame;
    private static final String HEAD_STRING = "History of commands: \n";
    private static final String JFRAME_TITLE = "Command History";
    private static final String HISTORY_TIME_PATTERN = "HH:mm:ss";

    public void changeWindowVisibility() {
        frame.setVisible(true);
    }

    public HistoryManagerWindow(DriverCommandManager commandManager) {
        this.commandManager = commandManager;
        frame = new JFrame(JFRAME_TITLE);
        frame.setSize(900, 500);
        Container content = frame.getContentPane();
        content.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;
        con.gridx = 0;
        con.weighty = 1;
        commandHistoryContent = new JTextArea(HEAD_STRING);
        commandHistoryContent.setEditable(false);
        content.add(commandHistoryContent, con);

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
    }

    public void updateCommandListField() {
        commandHistoryContent.append(java.time.LocalTime.now().format(DateTimeFormatter.ofPattern(HISTORY_TIME_PATTERN)) + ": " + commandManager.getCurrentCommandString() + "\n");
    }

    public void clearHistoryOfCommand() {
        if( commandHistoryContent != null )
            commandHistoryContent.setText(HEAD_STRING);
    }
    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(this.isVisible() ? false : true);
    }
}