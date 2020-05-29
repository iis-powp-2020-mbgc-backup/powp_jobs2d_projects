package edu.kis.powp.jobs2d.command.analyzer;

public enum Unit {
    NANOMETER,
    MICROMETER,
    MILLIMETER,
    CENTIMETER,
    DECIMETER,
    METER,
    KILOMETER;

    public static double mapUnit(Unit distanceUnit) {
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
}
