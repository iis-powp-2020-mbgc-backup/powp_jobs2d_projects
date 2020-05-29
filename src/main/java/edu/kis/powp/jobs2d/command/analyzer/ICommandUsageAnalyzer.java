package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.DriverCommand;

/**
 * This interface was defined for complex commands benchmarking and analysis of usage purpose.
 */
public interface ICommandUsageAnalyzer {

    /**
     * Performs command analysis based on injected policy.
     * @param compoundCommand command to benchmark.
     */
    void analyze(DriverCommand compoundCommand);

    /**
     * Sets current computation policy of analyzer.
     * @param policy policy to set.
     */
    void setComputationPolicy(IComputationPolicy policy);

    /**
     * Prepares legible summary of analysis.
     * @return analysis string
     */
    String exportStatistics();
}
