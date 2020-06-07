package edu.kis.powp.jobs2d.command.gui.backlog;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.events.SelectBackLogListOptionListener;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class BackLogManagerWindow extends JFrame implements WindowComponent {

    private DriverCommandManager commandManager;
    private JList<BackLogItem> backLogCommandList;


    public BackLogManagerWindow(DriverCommandManager commandManager) {
        this.setTitle("BackLog Viewer");
        this.setMinimumSize(new Dimension(500, 400));
        this.commandManager = commandManager;
        this.backLogCommandList = new JList<BackLogItem>(BackLogList.getBacklogCommandList());


        setupLog();


    }

    private void setupLog() {
        this.add(backLogCommandList);
        backLogCommandList.addListSelectionListener(
                new SelectBackLogListOptionListener(commandManager));
    }

    public void updateBackLogCommandList() {
        BackLogList.addCommand(commandManager.getCurrentCommandString(), commandManager.getCurrentCommand());

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
