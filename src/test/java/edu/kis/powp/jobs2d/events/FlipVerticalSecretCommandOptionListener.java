package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.DriverCommandFlipVertical;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class FlipVerticalSecretCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        DriverCommand testCommand = manager.getCurrentCommand();

        DriverCommandFlipVertical flipVisitor = new DriverCommandFlipVertical();
        testCommand.accept(flipVisitor);
        DriverCommand flipped = flipVisitor.getFlippedCommand();

        manager.setCurrentCommand(Arrays.asList(flipped), "CopiedTopSecretCommand");
    }

}
