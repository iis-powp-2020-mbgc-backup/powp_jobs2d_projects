package edu.kis.powp.jobs2d.command.history;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JToolBar;

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
        JToolBar toolBar = new JToolBar();
        JButton clearButton = new JButton("Clear history");
        clearButton.addActionListener((ActionEvent e) -> {
            CommandHistory.clearHistory();
        });
        toolBar.add(clearButton);
        toolBar.setFloatable(false);
        this.add(toolBar, BorderLayout.PAGE_START);
        this.add(historyCommandList);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

}
