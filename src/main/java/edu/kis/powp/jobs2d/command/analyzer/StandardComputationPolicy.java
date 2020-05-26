package edu.kis.powp.jobs2d.command.analyzer;

public class StandardComputationPolicy implements IComputationPolicy {

    private double deviceAcceleration;
    private double deviceDistanceFactor;
    private double deviceMaxVelocity;
    private Unit distanceUnit;

    /**
     * @inheritDoc
     */
    @Override
    public Statistics compute(int start_x, int start_y, int end_x, int end_y, UsageType type) {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setAccelerationFactor(double accelerationFactor) {
        deviceAcceleration = accelerationFactor;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDecelerationDistanceFactor(double decelerationDistanceFactor) {
        deviceDistanceFactor = decelerationDistanceFactor;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBasicDistanceUnit(Unit unit) {
        distanceUnit = unit;
    }
}
