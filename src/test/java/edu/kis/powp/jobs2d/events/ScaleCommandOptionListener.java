package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CommandSimpleTransform;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ScaleCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        DriverCommand testCommand = manager.getCurrentCommand();

        CommandSimpleTransform transformVisitor = new CommandSimpleTransform();
        transformVisitor.scale(.5f, 2.f);
        testCommand.accept(transformVisitor);
        DriverCommand flipped = transformVisitor.getTransformed();

        manager.setCurrentCommand(Arrays.asList(flipped), "CopiedTopSecretCommand");
    }
}
