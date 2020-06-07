package edu.kis.powp.jobs2d.command.gui.backlog;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class BackLogManagerWindow extends JFrame implements WindowComponent {

    private DriverCommandManager commandManager;
    private JList<BackLogItem> backLogCommandList;


    public BackLogManagerWindow(DriverCommandManager commandManager) {
        this.setTitle("BackLog Viewer");
        this.setSize(600, 500);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;
        this.backLogCommandList = new JList<BackLogItem>(BackLogList.getBacklogCommandList());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;

        setupLog();


    }

    private void setupLog() {
        this.add(backLogCommandList);
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
