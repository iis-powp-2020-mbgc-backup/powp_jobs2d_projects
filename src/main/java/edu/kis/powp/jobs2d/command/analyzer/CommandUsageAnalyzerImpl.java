package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.LinkedList;

public class CommandUsageAnalyzerImpl implements ICommandUsageAnalyzer {

    private IComputationPolicy policy;
    private LinkedList<Usage> usagesOfHead;
    private double timeOfUsage;

    /**
     * Start position of head
     */
    private int x = 0;
    private int y = 0;

    @Override
    public void analyze(ICompoundCommand compoundCommand) {
        timeOfUsage = 0;
        usagesOfHead = new LinkedList<>();
        visit(compoundCommand);
        usagesOfHead.forEach(usage -> this.timeOfUsage += policy.compute(usage.startX, usage.startY, usage.endX, usage.endY, usage.type));
    }

    @Override
    public void setComputationPolicy(IComputationPolicy policy) {
        this.policy = policy;
    }

    @Override
    public void visit(ICompoundCommand driverCommand) {
        for (DriverCommand stepCommand : driverCommand) {
            stepCommand.accept(this);
        }
    }

    @Override
    public void visit(OperateToCommand driverCommand) {
        Usage usage = new Usage(UsageType.WRITE, x, y, driverCommand.getPosX(), driverCommand.getPosY());
        usagesOfHead.add(usage);
        x = driverCommand.getPosX();
        y = driverCommand.getPosY();
    }

    @Override
    public void visit(SetPositionCommand driverCommand) {
        Usage usage = new Usage(UsageType.READ, x, y, driverCommand.getPosX(), driverCommand.getPosY());
        usagesOfHead.add(usage);
        x = driverCommand.getPosX();
        y = driverCommand.getPosY();
    }


    /**
     * internal struct
     */
    private static class Usage {
        UsageType type;
        int startX;
        int startY;
        int endX;
        int endY;

        Usage(UsageType type, int startX, int startY, int endX, int endY) {
            this.type = type;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }
}
