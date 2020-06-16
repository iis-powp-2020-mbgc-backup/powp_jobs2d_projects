package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.events.SelectHistoryListOptionListener;

import javax.swing.*;
import java.awt.*;

public class HistoryViewer extends JFrame implements WindowComponent {

    private static final long serialVersionUID = 1L;
    private final JList<HistoryEntry> historyCommandList;
    private final DefaultListModel<HistoryEntry> historyEntryDefaultListModel;
    private final DriverCommandManager driverCommandManager;

    public HistoryViewer(DefaultListModel<HistoryEntry> historyCommandList, DriverCommandManager driverCommandManager)
            throws HeadlessException {
        this.historyCommandList = new JList<>(historyCommandList);
        this.historyEntryDefaultListModel = historyCommandList;
        this.driverCommandManager = driverCommandManager;
        setupWindow();
    }

    private void setupWindow() {
        this.setMinimumSize(new Dimension(600, 300));
        this.setTitle("History");
        this.add(historyCommandList);
        historyCommandList.addListSelectionListener(
                new SelectHistoryListOptionListener(driverCommandManager, historyEntryDefaultListModel));
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

}
