package edu.kis.powp.jobs2d.command.analyzer;

/**
 * Model of computation defined for benchmarking of attached commands or in future whole devices.
 */
public interface IComputationPolicy {
    /**
     * Performs step computation, based on provided coordinates, device properties, and type of usage
     * @see edu.kis.powp.jobs2d.command.analyzer.UsageType
     * @param start_x current x position of head or other device
     * @param start_y current y position of head or other device
     * @param end_x end x position of head or other device
     * @param end_y end y position of head or other device
     * @return computed factors (i.e. time) for current step.
     */
    Statistics compute(int start_x, int start_y, int end_x, int end_y, UsageType type);

    /**
     * Sets acceleration factor for examined device or object
     * @param factor acceleration in m/s^2.
     */
    void setAccelerationFactor(double factor);

    /**
     * Sets deceleration distance factor for examined device or object
     * @param factor deceleration distance factor in units. Should be greater than 1.
     */
    void setDecelerationDistanceFactor(double factor);


    /**
     * Sets distance unit for current examination (it determines distances between coordinates)
     * @see #compute
     * @param unit unit enum
     * @see edu.kis.powp.jobs2d.command.analyzer.Unit
     */
    void setBasicDistanceUnit(Unit unit);

    /**
     * Indicates the impact of write usage in device foreseen performance.
     * @param factor
     */
    void setWriteUsageFactor(double factor);
}
