package edu.kis.powp.jobs2d.command.history;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.events.SelectHistoryListOptionListener;

public class HistoryViewer extends JFrame implements WindowComponent {

    private static final long serialVersionUID = 1L;
    private JList<HistoryEntry> historyCommandList;
    private DriverCommandManager driverCommandManager;

    public HistoryViewer(DefaultListModel<HistoryEntry> historyCommandList, DriverCommandManager driverCommandManager)
            throws HeadlessException {
        this.historyCommandList = new JList<HistoryEntry>(historyCommandList);
        this.driverCommandManager = driverCommandManager;
        setupWindow();
    }

    private void setupWindow() {
        this.setMinimumSize(new Dimension(600, 300));
        this.setTitle("History");
        this.add(historyCommandList);
        historyCommandList.addListSelectionListener(
                new SelectHistoryListOptionListener(driverCommandManager, historyCommandList));
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

}
