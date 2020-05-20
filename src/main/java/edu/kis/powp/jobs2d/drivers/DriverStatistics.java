package edu.kis.powp.jobs2d.drivers;

import java.text.DecimalFormat;
import java.util.logging.Logger;

/**
 * Class containing statistics for driver
 */
public class DriverStatistics {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private double overallDistance = 0;
    private double drawingDistance = 0;

    public void updateOverallDistance(double distance) {
        overallDistance += distance;
        updateLogger();
    }

    public  void updateDrawingDistance(double distance) {
        drawingDistance += distance;
    }

    public void updateLogger() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        logger.info("Overall Distance: " + decimalFormat.format(overallDistance) +
                "\nDrawing Distance: " + decimalFormat.format(drawingDistance));
    }
}
