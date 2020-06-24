package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.CommandUsageCounterVisitor;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class SelectCommandUsageCounterVisitorTestListener implements ActionListener {
    private Logger logger = Logger.getLogger("global");

    @Override
    public void actionPerformed(ActionEvent e) {

        List<DriverCommand> commands = new ArrayList<>(
                Arrays.asList(
                        new SetPositionCommand(10, 1),
                        new OperateToCommand(10, 1),
                        new SetPositionCommand(10, 0)
                )
        );

        this.logger.info("Testing visitor for compound command.");
        ICompoundCommand embeddedCompoundCommand = new ICompoundCommand() {
            private List<DriverCommand> embeddedCommands = new ArrayList<>(
                    Arrays.asList(
                            new OperateToCommand(10, 5),
                            new OperateToCommand(5, 5)
                    )
            );

            @Override
            public Iterator<DriverCommand> iterator() {
                return embeddedCommands.iterator();
            }

            @Override
            public void execute(Job2dDriver driver) {

            }

            @Override
            public DriverCommand clone() throws CloneNotSupportedException {
                return null;
            }
        };
        commands.add(embeddedCompoundCommand);
        ICompoundCommand command = new ICompoundCommand() {
            @Override
            public Iterator<DriverCommand> iterator() {
                return commands.iterator();
            }

            @Override
            public void execute(Job2dDriver driver) {

            }

            @Override
            public DriverCommand clone() throws CloneNotSupportedException {
                return null;
            }
        };
        CommandUsageCounterVisitor visitor = new CommandUsageCounterVisitor();
        command.accept(visitor);
        logger.info("OperateTo Counter: " + visitor.getOperateToCounter());
        logger.info("SetPosition Counter: " + visitor.getSetPositionCounter());
    }
}