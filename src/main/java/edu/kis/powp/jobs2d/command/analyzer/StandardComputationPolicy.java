package edu.kis.powp.jobs2d.command.analyzer;

public class StandardComputationPolicy implements IComputationPolicy {

    private double deviceAcceleration;
    private double deviceDistanceFactor;
    private Unit distanceUnit;
    private double writeFactor;

    public StandardComputationPolicy(ComputationPolicyBuilder computationPolicyBuilder) {
        this.deviceDistanceFactor = computationPolicyBuilder.distanceFactor;
        this.distanceUnit = computationPolicyBuilder.unit;
        this.writeFactor = computationPolicyBuilder.writeUsage;
        this.deviceAcceleration = computationPolicyBuilder.deviceAcceleration;
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

        double distance = Math.sqrt(Math.pow(end_x - start_x, 2) + Math.pow(end_y - start_y, 2)) * unit;
        double coefficient = 2 * (1 - 1 / deviceDistanceFactor) * distance;
        double factor = (deviceDistanceFactor / (deviceDistanceFactor - 1));
        double time = Math.sqrt(coefficient / deviceAcceleration) * factor * writeFactor;
        double averageVelocity = distance / time;

        statistics.addRecord("time", time);
        statistics.addRecord("averageVelocity", averageVelocity);
        statistics.addRecord("distance", distance);

        return statistics;
    }

    private double mapUnit(Unit distanceUnit) {
        switch (distanceUnit) {
            case NANOMETER:
                return Math.pow(10, -9);
            case MICROMETER:
                return Math.pow(10, -6);
            case MILLIMETER:
                return Math.pow(10, -3);
            case CENTIMETER:
                return Math.pow(10, -2);
            case DECIMETER:
                return Math.pow(10, -1);
            case METER:
                return 1;
            case KILOMETER:
                return Math.pow(10, 3);
            default:
                throw new IllegalStateException("Unexpected value: " + distanceUnit);
        }
    }

    public static class ComputationPolicyBuilder {

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

        public IComputationPolicy build() {
            return new StandardComputationPolicy(this);
        }
    }
}
