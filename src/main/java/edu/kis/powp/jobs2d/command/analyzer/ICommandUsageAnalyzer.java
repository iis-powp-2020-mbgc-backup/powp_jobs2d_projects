package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.DriverCommandVisitor;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

public interface ICommandUsageAnalyzer extends DriverCommandVisitor {
    void analyze(ICompoundCommand compoundCommand);
    void setComputationPolicy(IComputationPolicy policy);
}
