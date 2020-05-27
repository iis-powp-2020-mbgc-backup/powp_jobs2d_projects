package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.LinkedList;
import java.util.List;

import static edu.kis.powp.jobs2d.command.analyzer.StatisticType.*;

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
    public void analyze(DriverCommand compoundCommand) {

        timeOfUsages = new LinkedList<>();
        distances = new LinkedList<>();
        averageVelocities = new LinkedList<>();
        inkUsages = new LinkedList<>();

        visitor.setStartPosition(startX, startY);
        compoundCommand.accept(visitor);

        LinkedList<Usage> usagesOfHead = visitor.getUsages();
        usagesOfHead.forEach(usage -> {
            Statistics statistics = policy.compute(usage.getStartX(), usage.getStartY(), usage.getEndX(), usage.getEndY(), usage.getType());
            this.timeOfUsages.add(statistics.getData(TOTAL_TIME));
            this.averageVelocities.add(statistics.getData(AVERAGE_VELOCITY));
            this.distances.add(statistics.getData(TOTAL_DISTANCE));
            this.inkUsages.add(statistics.getData(TOTAL_INK_USAGE));
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

        sessionStatistics.addRecord(AVERAGE_TIME, averageTime);
        sessionStatistics.addRecord(TOTAL_TIME, totalTime);
        sessionStatistics.addRecord(AVERAGE_VELOCITY, averageVelocity);
        sessionStatistics.addRecord(AVERAGE_DISTANCE, averageDistance);
        sessionStatistics.addRecord(TOTAL_DISTANCE, totalDistance);
        sessionStatistics.addRecord(AVERAGE_INK_USAGE, averageInk);
        sessionStatistics.addRecord(TOTAL_INK_USAGE, totalInk);
    }

    public String exportStatistics() {
        return sessionStatistics.toString();
    }

    @Override
    public void setComputationPolicy(IComputationPolicy policy) {
        this.policy = policy;
    }
}
