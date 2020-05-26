package edu.kis.powp.jobs2d.command.analyzer;

public class StandardComputationPolicy implements IComputationPolicy {

    private double deviceAcceleration;
    private double deviceDistanceFactor;
    private Unit distanceUnit;
    private double writeFactor;

    /**
     * @inheritDoc
     */
    @Override
    public Statistics compute(int start_x, int start_y, int end_x, int end_y, UsageType type) {
        Statistics statistics = new Statistics();

        double unit = mapUnit(distanceUnit);

        double distance = Math.sqrt(Math.pow(end_x - start_x, 2) + Math.pow(end_y - start_y, 2)) * unit;
        double coefficient = 2 * (1 - 1/deviceDistanceFactor) * distance;
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

    /**
     * @inheritDoc
     */
    @Override
    public void setAccelerationFactor(double factor) {
        deviceAcceleration = factor;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDecelerationDistanceFactor(double factor) {
        if(factor < 1) throw new RuntimeException("Deceleration distance factor is lesser than 1");
        deviceDistanceFactor = factor;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBasicDistanceUnit(Unit unit) {
        distanceUnit = unit;
    }

    /**
     * Indicates the impact of write usage in device foreseen performance.
     *
     * @param factor
     */
    @Override
    public void setWriteUsageFactor(double factor) {
        writeFactor = factor;
    }
}
