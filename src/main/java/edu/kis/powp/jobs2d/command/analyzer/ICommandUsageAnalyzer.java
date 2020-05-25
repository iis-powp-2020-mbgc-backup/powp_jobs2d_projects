package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.DriverCommandVisitor;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

/**
 * This interface was defined for complex commands benchmarking and analysis of usage purpose.
 */
public interface ICommandUsageAnalyzer extends DriverCommandVisitor {
    /**
     * Performs command analysis based on injected policy.
     * @param compoundCommand command to benchmark.
     */
    void analyze(ICompoundCommand compoundCommand);

    /**
     * Sets current computation policy of analyzer.
     * @param policy policy to set.
     */
    void setComputationPolicy(IComputationPolicy policy);
}
