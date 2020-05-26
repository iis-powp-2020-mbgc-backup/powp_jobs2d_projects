package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.analyzer.*;

public class AnalyzerFeature {

    private static CommandUsageAnalyzerImpl analyzer = null;

    public static void setUpAnalyzer(){
        analyzer = new CommandUsageAnalyzerImpl();
        IComputationPolicy policy = StandardComputationPolicy.computationPolicyBuilder()
                .ofDistanceUnit(Unit.MICROMETER)
                .ofDeviceAcceleration(5)
                .ofDecelerationDistanceFactor(3)
                .ofWriteUsageFactor(2).build();
        analyzer.setComputationPolicy(policy);
        analyzer.setStartPosition(0, 0);
    }

    public static ICommandUsageAnalyzer getAnalyzer(){
        return analyzer;
    }
}
