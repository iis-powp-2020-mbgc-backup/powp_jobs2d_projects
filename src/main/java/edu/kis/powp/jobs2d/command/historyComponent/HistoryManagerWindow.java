package edu.kis.powp.jobs2d.command.historyComponent;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.events.SelectHistoryListOptionListener;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class HistoryManagerWindow extends JFrame implements WindowComponent {

    private JList<CommandHistory> entityHistory;
    private final DefaultListModel<CommandHistory> historyEntryDefaultListModel;
    private DriverCommandManager commandManager;
    private JFrame frame;
    Dimension screenSize;
    private static final String HEAD_STRING = "History of commands: \n";
    private static final String JFRAME_TITLE = "Command History";
    private static final String HISTORY_TIME_PATTERN = "HH:mm:ss";

    public void changeWindowVisibility() {
        frame.setVisible(true);
    }

    public HistoryManagerWindow(DriverCommandManager commandManager) {
        DefaultListModel<CommandHistory> entityHistoryNewListModel = new DefaultListModel<>();
        this.entityHistory = new JList<>(entityHistoryNewListModel);
        this.historyEntryDefaultListModel = entityHistoryNewListModel;
        this.add(entityHistory);
        this.entityHistory.addListSelectionListener(
                new SelectHistoryListOptionListener(commandManager, historyEntryDefaultListModel));

        this.commandManager = commandManager;
        frame = new JFrame(JFRAME_TITLE);
        entityHistory.setPreferredSize(new Dimension(500, 500));
        screenSize = new Dimension(600, 300);
        frame.add(new JScrollPane(entityHistory));
        frame.setPreferredSize(screenSize);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
    }

    public void updateCommandListField() {
        CommandHistory history = new CommandHistory(java.time.LocalTime.now().format(DateTimeFormatter.ofPattern(HISTORY_TIME_PATTERN)),
                commandManager.getCurrentCommandString(), commandManager.getCurrentCommand());
        System.out.println(history);
        System.out.println(commandManager.getCurrentCommandString());
        System.out.println(commandManager.getCurrentCommandString());
        System.out.println(commandManager.getCurrentCommand());
        historyEntryDefaultListModel.addElement(history);
    }

    public void clearHistoryOfCommand() {
        if( historyEntryDefaultListModel != null )
            historyEntryDefaultListModel.clear();
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(this.isVisible() ? false : true);
    }
}