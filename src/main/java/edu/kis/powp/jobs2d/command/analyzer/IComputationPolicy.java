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
     * @return computed factor (i.e. time) for current step.
     */
    double compute(int start_x, int start_y, int end_x, int end_y, UsageType type);

    /**
     * Sets acceleration factor for examined device or object
     * @param d acceleration in m/s^2.
     */
    void setAccelerationFactor(double d);

    /**
     * Sets deceleration factor for examined device or object
     * @param d deceleration in m/s^2.
     */
    void setDecelerationFactor(double d);

    /**
     * Sets velocity factor for examined device or object
     * @param d deceleration in m/s^2.
     */
    void setVelocity(double d);

    /**
     * Sets distance unit for current examination (it determines distances between coordinates)
     * @see #compute
     * @param unit unit enum
     * @see edu.kis.powp.jobs2d.command.analyzer.Unit
     */
    void setBasicDistanceUnit(Unit unit);
}
