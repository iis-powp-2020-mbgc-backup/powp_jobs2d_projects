package edu.kis.powp.jobs2d.command.manager;

public interface IComputationPolicy {
    double compute(double start_x, double start_y, double end_x, double end_y, UsageType type);
}
