package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverStatistics;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Line adapter - Job2dDriver with DrawPanelController object.
 */
public class LineDriverAdapter implements Job2dDriver {
    private ILine line;
    private int startX = 0, startY = 0;
    private String name;
    private DriverStatistics statistics;

    private DrawPanelController drawController;

    public LineDriverAdapter(DrawPanelController drawController, ILine line, String name, DriverStatistics statistics) {
        super();
        this.drawController = drawController;
        this.line = line;
        this.name = name;
        this.statistics = statistics;
    }

    @Override
    public void setPosition(int x, int y) {
        statistics.updateOverallDistance(calculateDistance(x, y));
        this.startX = x;
        this.startY = y;
    }

    @Override
    public void operateTo(int x, int y) {
        statistics.updateDrawingDistance(calculateDistance(x, y));
        line.setStartCoordinates(this.startX, this.startY);
        this.setPosition(x, y);
        line.setEndCoordinates(x, y);

        drawController.drawLine(line);
    }

    private double calculateDistance(int x, int y)
    {
        return sqrt(pow(startX - x, 2) + pow(startY - y, 2));
    }

    @Override
    public String toString() {
        return "2d device simulator - " + name;
    }
}
