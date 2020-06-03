package edu.kis.powp.jobs2d.command.history;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import edu.kis.powp.appbase.gui.WindowComponent;

public class HistoryViewer extends JFrame implements WindowComponent {

    private static final long serialVersionUID = 1L;
    private JList<HistoryEntry> historyCommandList;

    public HistoryViewer(DefaultListModel<HistoryEntry> historyCommandList) throws HeadlessException {
        this.historyCommandList = new JList<HistoryEntry>(historyCommandList);
        setUpWindow();
    }

    private void setUpWindow() {
        this.setMinimumSize(new Dimension(600, 300));
        this.setTitle("History");
        this.add(historyCommandList);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

}
