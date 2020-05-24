package edu.kis.powp.jobs2d.command.analyzer;

public interface IComputationPolicy {
    double compute(int start_x, int start_y, int end_x, int end_y, UsageType type);
    void setAccelerationFactor(double d);
    void setDecelerationFactor(double d);
    void setVelocity(double d);
    void setBasicDistanceUnit(Unit unit);
}
