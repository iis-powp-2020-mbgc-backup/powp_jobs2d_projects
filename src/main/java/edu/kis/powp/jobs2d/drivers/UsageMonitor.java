package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.text.DecimalFormat;
import java.util.logging.Logger;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class UsageMonitor implements Job2dDriver {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static double nonOperationalDistance = 0;
    private static double operationalDistance = 0;

    private double lastX = 0;
    private double lastY = 0;

    private Job2dDriver driver;

    public UsageMonitor(Job2dDriver driver) {
        this.driver = driver;
    }

    @Override
    public void setPosition(int x, int y) {
        nonOperationalDistance += calculateDistance(x, y);
        driver.setPosition(x, y);

        lastX = x;
        lastY = y;

        updateLogger();
    }

    @Override
    public void operateTo(int x, int y) {
        operationalDistance += calculateDistance(x, y);
        driver.operateTo(x, y);

        lastX = x;
        lastY = y;

        updateLogger();
    }

    private double calculateDistance(int x, int y)
    {
        return sqrt(pow(lastX - x, 2) + pow(lastY - y, 2));
    }

    private void updateLogger() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        logger.info("Non operational distance: " + decimalFormat.format(nonOperationalDistance) +
                    "\n   Operational distance: " + decimalFormat.format(operationalDistance));
    }

    /**
     * API go get distance made by operateTo method by all drivers
     * @return distance made by setPosition method
     */
    public static double getOperationalDistance() {
        return operationalDistance;
    }

    /**
     * API go get distance made by setPosition method by all drivers
     * @return distance made by setPosition method
     */
    public static double getNonOperationalDistance() {
        return operationalDistance;
    }
}
