package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.command.history.CommandHistory;

public class SelectClearHistoryListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandHistory.clearHistory();
    }

}
