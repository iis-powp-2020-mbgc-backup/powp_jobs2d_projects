package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ICompoundCommandDeepCopyTest implements ActionListener {

    private DriverManager driverManager;

    public ICompoundCommandDeepCopyTest(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<DriverCommand> commands = new ArrayList<DriverCommand>();

        SetPositionCommand setCommand = new SetPositionCommand(0, 0);
        OperateToCommand firstCommand = new OperateToCommand(0, -100);
        OperateToCommand secondCommand = new OperateToCommand(30, -100);

        commands.add(setCommand);
        commands.add(firstCommand);
        commands.add(secondCommand);
        ICompoundCommandImpl command = new ICompoundCommandImpl(commands);

        ICompoundCommandImpl copy = null;
        try {
            copy = command.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }

        firstCommand.movePoint(20, 20);
        secondCommand.movePoint(20, 20);
        setCommand.movePoint(20, 20);

        command.execute(driverManager.getCurrentDriver());
        copy.execute(driverManager.getCurrentDriver());
    }
}