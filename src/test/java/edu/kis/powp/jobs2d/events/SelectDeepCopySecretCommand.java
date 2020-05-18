package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectDeepCopySecretCommand implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CompoundCommand compoundCommands;

        List<DriverCommand> commands = new ArrayList<DriverCommand>();
        try {
            commands.add(new SetPositionCommand(-20, -50));
            commands.add(new OperateToCommand(-20, -50));
            commands.add(new SetPositionCommand(-20, -40));
            commands.add(new OperateToCommand(-20, 50));
            commands.add(new SetPositionCommand(0, -50));
            commands.add(new OperateToCommand(0, -50));
            commands.add(new SetPositionCommand(0, -40));
            commands.add(new OperateToCommand(0, 50));
            commands.add(new SetPositionCommand(70, -50));
            commands.add(new OperateToCommand(20, -50));
            commands.add(new OperateToCommand(20, 0));
            commands.add(new OperateToCommand(70, 0));
            commands.add(new OperateToCommand(70, 50));
            commands.add(new OperateToCommand(20, 50));

            compoundCommands = new CompoundCommand(commands);
            DriverCommand driverCommand = CommandsFeature.deepCommandCopy(compoundCommands);

            commands.clear();
            commands.add(driverCommand);

        }catch (CloneNotSupportedException e){
            return;
        }

        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        manager.setCurrentCommand(commands, "TopSecretCommandDeepCopy");
    }
}
