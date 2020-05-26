package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.DriverCommandRotate90Degrees;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RotateRightSecretCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        DriverCommand testCommand = manager.getCurrentCommand();

        DriverCommandRotate90Degrees rotateVisitor = new DriverCommandRotate90Degrees(DriverCommandRotate90Degrees.Direction.RIGHT);
        testCommand.accept(rotateVisitor);
        DriverCommand flipped = rotateVisitor.getRotatedCommand();

        manager.setCurrentCommand(Arrays.asList(flipped), "CopiedTopSecretCommand");
    }
}
