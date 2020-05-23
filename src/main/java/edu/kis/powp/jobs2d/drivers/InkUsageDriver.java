package edu.kis.powp.jobs2d.drivers;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

public class InkUsageDriver implements Job2dDriver {
    private int x, y;
    Logger logger = Logger.getLogger("global");
    private DrawPanelController drawController;
    private ILine line;


    public InkUsageDriver(ILine line, DrawPanelController drawController){
        super();
        this.drawController = drawController;
        this.line = line;
    }

    @Override
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    double inkCounter(int xStart, int xEnd, int yStart, int yEnd){
        double count = 0.0;
        count = Math.sqrt(Math.pow(xStart - xEnd, 2.0) + Math.pow(yStart - yEnd, 2.0));
        return count;
    }

    @Override
    public void operateTo(int i, int i1) {

        line.setStartCoordinates(this.x, this.y);
        this.setPosition(x, y);
        line.setEndCoordinates(x, y);

        drawController.drawLine(line);

        double waste = 0;
        int xStart = line.getStartCoordinateX();
        int xEnd = line.getEndCoordinateX();
        int yStart = line.getStartCoordinateY();
        int yEnd = line.getEndCoordinateY();

        waste = inkCounter(xStart, xEnd, yStart, yEnd);
        this.logger.info("Ink: " + String.format ("%.3f", waste));
    }
}
