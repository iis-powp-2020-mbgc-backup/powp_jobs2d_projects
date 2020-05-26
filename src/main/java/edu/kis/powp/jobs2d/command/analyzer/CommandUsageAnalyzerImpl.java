package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.LinkedList;
import java.util.List;

public class CommandUsageAnalyzerImpl implements ICommandUsageAnalyzer {

    private IComputationPolicy policy;
    private LinkedList<Usage> usagesOfHead;
    private List<Double> timeOfUsages;
    private List<Double> averageVelocities;
    private List<Double> distances;
    private Statistics sessionStatistics;

    private int startX;
    private int startY;

    public void setStartPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    /**
     * Start position of head during computation
     */
    private int x = 0;
    private int y = 0;

    @Override
    public void analyze(ICompoundCommand compoundCommand) {

        timeOfUsages = new LinkedList<>();
        usagesOfHead = new LinkedList<>();
        distances = new LinkedList<>();
        averageVelocities = new LinkedList<>();
        x = startX;
        y = startY;

        visit(compoundCommand);

        usagesOfHead.forEach(usage -> {
            Statistics statistics = policy.compute(usage.startX, usage.startY, usage.endX, usage.endY, usage.type);
            this.timeOfUsages.add(statistics.getData("time"));
            this.averageVelocities.add(statistics.getData("averageVelocity"));
            this.distances.add(statistics.getData("distance"));
        });

        applyResults();
    }

    private void applyResults() {
        sessionStatistics = new Statistics();

        double averageTime = this.timeOfUsages.stream()
                .mapToDouble(value -> value)
                .average()
                .orElse(Double.NaN);

        double totalTime = this.timeOfUsages.stream()
                .mapToDouble(value -> value)
                .sum();

        double averageVelocity = this.averageVelocities.stream()
                .mapToDouble(value -> value)
                .average()
                .orElse(Double.NaN);

        double totalDistance = this.distances.stream()
                .mapToDouble(value -> value)
                .sum();

        double averageDistance = this.distances.stream()
                .mapToDouble(value -> value)
                .average()
                .orElse(Double.NaN);

        sessionStatistics.addRecord("averageTime", averageTime);
        sessionStatistics.addRecord("totalTime", totalTime);
        sessionStatistics.addRecord("averageVelocity", averageVelocity);
        sessionStatistics.addRecord("averageDistance", averageDistance);
        sessionStatistics.addRecord("totalDistance", totalDistance);
    }

    public String exportStatistics(){
        return sessionStatistics.toString();
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
