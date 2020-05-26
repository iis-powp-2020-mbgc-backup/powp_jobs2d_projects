package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.ICompoundCommand;

import java.util.LinkedList;
import java.util.List;

public class CommandUsageAnalyzerImpl implements ICommandUsageAnalyzer {

    private IComputationPolicy policy;
    private List<Double> timeOfUsages;
    private List<Double> averageVelocities;
    private List<Double> distances;
    private List<Double> inkUsages;
    private Statistics sessionStatistics;
    private CommandUsageVisitor visitor;
    private int startX;
    private int startY;

    public CommandUsageAnalyzerImpl() {
        this.visitor = new CommandUsageVisitor();
    }


    public void setStartPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    @Override
    public void analyze(ICompoundCommand compoundCommand) {

        timeOfUsages = new LinkedList<>();
        distances = new LinkedList<>();
        averageVelocities = new LinkedList<>();
        inkUsages = new LinkedList<>();

        visitor.setStartPosition(startX, startY);
        visitor.visit(compoundCommand);

        LinkedList<Usage> usagesOfHead = visitor.getUsages();
        usagesOfHead.forEach(usage -> {
            Statistics statistics = policy.compute(usage.getStartX(), usage.getStartY(), usage.getEndX(), usage.getEndY(), usage.getType());
            this.timeOfUsages.add(statistics.getData("time"));
            this.averageVelocities.add(statistics.getData("averageVelocity"));
            this.distances.add(statistics.getData("distance"));
            this.inkUsages.add(statistics.getData("totalInkUsed"));
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

        double totalInk = this.inkUsages.stream()
                .mapToDouble(value -> value)
                .sum();

        double averageInk = this.inkUsages.stream()
                .mapToDouble(value -> value)
                .average()
                .orElse(Double.NaN);

        sessionStatistics.addRecord("averageTime", averageTime);
        sessionStatistics.addRecord("totalTime", totalTime);
        sessionStatistics.addRecord("averageVelocity", averageVelocity);
        sessionStatistics.addRecord("averageDistance", averageDistance);
        sessionStatistics.addRecord("totalDistance", totalDistance);
        sessionStatistics.addRecord("averageInkUsage", averageInk);
        sessionStatistics.addRecord("totalInkUsage", totalInk);
    }

    public String exportStatistics() {
        return sessionStatistics.toString();
    }

    @Override
    public void setComputationPolicy(IComputationPolicy policy) {
        this.policy = policy;
    }
}
