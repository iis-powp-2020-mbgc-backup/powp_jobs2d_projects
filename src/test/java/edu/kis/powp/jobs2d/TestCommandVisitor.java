package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Command Visitor implementation test.
 *
 * @author Michał Kubik & Bartosz Gralewski
 */
public class TestCommandVisitor {
    private static final Job2dDriver driver = new StubDriver();

    /**
     * Command Visitor test.
     */
    public static void main(String[] args) {

        ArrayList<DriverCommand> commandsList = new ArrayList<>(3);
        commandsList.add(new OperateToCommand(1, 2));
        commandsList.add(new SetPositionCommand(2, 3));
        commandsList.add(new OperateToCommand(3, 4));

        ICompoundCommand compoundCommand = new ICompoundCommand() {
            final List<DriverCommand> driverCommands = commandsList;


            @Override
            public Iterator<DriverCommand> iterator() {
                return driverCommands.iterator();
            }

            @Override
            public void execute(Job2dDriver driver) {

            }

            @Override
            public void accept(ICommandVisitor commandVisitor) {
                for (DriverCommand elem : driverCommands) {
                    if (elem instanceof SetPositionCommand)
                        commandVisitor.visit((SetPositionCommand) elem);
                    if (elem instanceof OperateToCommand)
                        commandVisitor.visit((OperateToCommand) elem);
                    if (elem instanceof ICompoundCommand)
                        commandVisitor.visit((ICompoundCommand) elem);
                }
            }
        };

        Visitor commandVisitor = new Visitor(driver);

        commandVisitor.visit(compoundCommand);

        if (commandVisitor.operateToVisits != 2) {
            System.err.println("Incorrect OperateToCommand invocations count, should be 2, got " + commandVisitor.operateToVisits + " instead.");
        }
        if (commandVisitor.setPositionVisits != 1) {
            System.err.println("Incorrect SetPositionCommand invocations count, should be 1, got " + commandVisitor.setPositionVisits + " instead.");
        }
        if (commandVisitor.compoundCommandVisits != 1) {
            System.err.println("Incorrect CompoundCommand invocations count, should be 1, got " + commandVisitor.compoundCommandVisits + " instead.");
        }
    }

    private static class StubDriver implements Job2dDriver {

        @Override
        public void operateTo(int x, int y) {
            System.out.println("Driver operateTo action...");
        }

        @Override
        public void setPosition(int x, int y) {
            System.out.println("Driver setPosition action...");
        }
    }

    private static class Visitor implements ICommandVisitor {
        public int operateToVisits = 0;
        public int setPositionVisits = 0;
        public int compoundCommandVisits = 0;

        private final Job2dDriver job2dDriver;

        Visitor(Job2dDriver job2dDriver) {
            this.job2dDriver = job2dDriver;
        }

        @Override
        public void visit(SetPositionCommand setPositionCommand) {
            setPositionVisits++;
            setPositionCommand.execute(job2dDriver);
        }

        @Override
        public void visit(OperateToCommand operateToCommand) {
            operateToVisits++;
            operateToCommand.execute(job2dDriver);
        }

        @Override
        public void visit(ICompoundCommand compoundCommand) {
            compoundCommandVisits++;
            compoundCommand.accept(this);
        }

    }
}
