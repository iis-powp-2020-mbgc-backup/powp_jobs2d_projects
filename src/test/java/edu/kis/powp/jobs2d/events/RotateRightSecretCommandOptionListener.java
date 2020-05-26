package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.DriverCommandRotate90degrees;
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

        DriverCommandRotate90degrees rotateVisitor = new DriverCommandRotate90degrees(DriverCommandRotate90degrees.Direction.RIGHT);
        testCommand.accept(rotateVisitor);
        DriverCommand flipped = rotateVisitor.getRotatedCommand();

        manager.setCurrentCommand(Arrays.asList(flipped), "CopiedTopSecretCommand");
    }
}
