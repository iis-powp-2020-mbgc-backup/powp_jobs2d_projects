package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.*;

import java.util.LinkedList;

public class CommandUsageVisitor implements DriverCommandVisitor {

    private LinkedList<Usage> usages;

    /**
     * Start position of head during computation
     */
    private int x = 0;
    private int y = 0;

    @Override
    public void visit(ICompoundCommand driverCommand) {
        usages = new LinkedList<>();
        for (DriverCommand stepCommand : driverCommand) {
            stepCommand.accept(this);
        }
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        Usage usage = new Usage(UsageType.WRITE, x, y, driverCommand.getPosX(), driverCommand.getPosY());
        usages.add(usage);
        x = driverCommand.getPosX();
        y = driverCommand.getPosY();
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        Usage usage = new Usage(UsageType.READ, x, y, driverCommand.getPosX(), driverCommand.getPosY());
        usages.add(usage);
        x = driverCommand.getPosX();
        y = driverCommand.getPosY();
    }

    public LinkedList<Usage> getUsages() {
        return usages;
    }

    public void setStartPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
