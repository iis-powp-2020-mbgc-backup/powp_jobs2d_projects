package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommandInstance;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ICompoundCommandListener implements ActionListener {

    private DriverManager driverManager;

    public ICompoundCommandListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<DriverCommand> commands = new ArrayList<DriverCommand>();
        commands.add(new OperateToCommand(0, -100));
        commands.add(new OperateToCommand(30, -100));

        ICompoundCommandInstance command = new ICompoundCommandInstance(commands);

        ICompoundCommandInstance copy = null;
        try {
            copy = command.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        commands.add(new OperateToCommand(500, 500));
        copy.execute(driverManager.getCurrentDriver());
    }
}