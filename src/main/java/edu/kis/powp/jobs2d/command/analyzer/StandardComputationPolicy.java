package edu.kis.powp.jobs2d.command.analyzer;

import static edu.kis.powp.jobs2d.command.analyzer.StatisticType.*;
import static edu.kis.powp.jobs2d.command.analyzer.Unit.mapUnit;

public class StandardComputationPolicy implements IComputationPolicy {

    private final double inkFactor;
    private double deviceAcceleration;
    private double deviceDistanceFactor;
    private Unit distanceUnit;
    private double writeFactor;

    public StandardComputationPolicy(ComputationPolicyBuilder computationPolicyBuilder) {
        this.deviceDistanceFactor = computationPolicyBuilder.distanceFactor;
        this.distanceUnit = computationPolicyBuilder.unit;
        this.writeFactor = computationPolicyBuilder.writeUsage;
        this.deviceAcceleration = computationPolicyBuilder.deviceAcceleration;
        this.inkFactor = computationPolicyBuilder.inkFactor;
    }

    public static ComputationPolicyBuilder computationPolicyBuilder() {
        return new ComputationPolicyBuilder();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Statistics compute(int start_x, int start_y, int end_x, int end_y, UsageType type) {
        Statistics statistics = new Statistics();

        double unit = mapUnit(distanceUnit);

        double distance = Math.sqrt(Math.pow(end_x - start_x + 1, 2) + Math.pow(end_y - start_y + 1, 2)) * unit;
        double coefficient = 2 * (1 - 1 / deviceDistanceFactor) * distance;
        double factor = (deviceDistanceFactor / (deviceDistanceFactor - 1));
        double time = Math.sqrt(coefficient / deviceAcceleration) * factor * writeFactor;
        double averageVelocity = distance / time;
        averageVelocity = Double.isNaN(averageVelocity) ? 0 : averageVelocity;
        double totalInk = type == UsageType.WRITE ? distance / inkFactor : 0;

        statistics.addRecord(TOTAL_TIME, time);
        statistics.addRecord(AVERAGE_VELOCITY, averageVelocity);
        statistics.addRecord(TOTAL_DISTANCE, distance);
        statistics.addRecord(TOTAL_INK_USAGE, totalInk);
        return statistics;
    }

    public static class ComputationPolicyBuilder {

        private double inkFactor;
        private Unit unit;
        private double writeUsage;
        private double deviceAcceleration;
        private double distanceFactor;

        /**
         * Sets distance unit for current examination (it determines distances between coordinates)
         *
         * @param unit unit enum
         * @see StandardComputationPolicy#compute
         * @see edu.kis.powp.jobs2d.command.analyzer.Unit
         */
        public ComputationPolicyBuilder ofDistanceUnit(Unit unit) {
            this.unit = unit;
            return this;
        }

        /**
         * Sets acceleration factor for examined device or object
         *
         * @param deviceAcceleration acceleration in m/s^2.
         */
        public ComputationPolicyBuilder ofDeviceAcceleration(double deviceAcceleration) {
            this.deviceAcceleration = deviceAcceleration;
            return this;
        }

        /**
         * Sets deceleration distance factor for examined device or object
         *
         * @param factor deceleration distance factor in units. Should be greater than 1.
         */
        public ComputationPolicyBuilder ofDecelerationDistanceFactor(double factor) {
            if (factor < 1) throw new RuntimeException("Deceleration distance factor is lesser than 1");
            this.distanceFactor = factor;
            return this;
        }

        /**
         * Indicates the impact of write usage in device foreseen performance.
         *
         * @param value impact of write usage
         */
        public ComputationPolicyBuilder ofWriteUsageFactor(double value) {
            this.writeUsage = value;
            return this;
        }

        /**
         * Sets the amount of litres of ink that will be used per one meter of distance.
         *
         * @param v factor
         * @return this
         */
        public ComputationPolicyBuilder ofInkUsageFactor(double v) {
            this.inkFactor = v;
            return this;
        }

        /**
         * builds policy
         *
         * @return new instance of policy filled with data from this class.
         */
        public IComputationPolicy build() {
            return new StandardComputationPolicy(this);
        }
    }
}
