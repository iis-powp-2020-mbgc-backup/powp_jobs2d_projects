package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.LinkedList;

public class CommandUsageAnalyzerImpl implements ICommandUsageAnalyzer {

    private IComputationPolicy policy;
    private LinkedList<Usage> usagesOfHead;
    private double timeOfUsage;


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

    }

    @Override
    public void visit(OperateToCommand driverCommand) {

    }

    @Override
    public void visit(SetPositionCommand driverCommand) {

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
    }
}
