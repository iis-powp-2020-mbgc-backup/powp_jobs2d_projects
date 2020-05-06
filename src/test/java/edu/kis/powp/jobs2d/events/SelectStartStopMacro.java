package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.manager.MacroCommandManager;
import edu.kis.powp.jobs2d.features.MacroFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectStartStopMacro implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        MacroCommandManager manager = MacroFeature.getDriverCommandManager();
        manager.changeMacroState();
    }
}