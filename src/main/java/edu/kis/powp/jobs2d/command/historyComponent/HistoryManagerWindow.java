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
    private JFrame frame;
    Dimension screenSize;
    private static final String JFRAME_TITLE = "Command History";

    public void changeWindowVisibility() {
        frame.setVisible(true);
    }

    public HistoryManagerWindow(DriverCommandManager commandManager) {
        this.entityHistory = new JList<>(HistoryManager.historyEntryDefaultListModel);
        this.add(entityHistory);
        this.entityHistory.addListSelectionListener(
                new SelectHistoryListOptionListener(commandManager, HistoryManager.historyEntryDefaultListModel));

        frame = new JFrame(JFRAME_TITLE);
        entityHistory.setPreferredSize(new Dimension(500, 500));
        screenSize = new Dimension(600, 300);
        frame.add(new JScrollPane(entityHistory));
        frame.setPreferredSize(screenSize);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(this.isVisible() ? false : true);
    }
}