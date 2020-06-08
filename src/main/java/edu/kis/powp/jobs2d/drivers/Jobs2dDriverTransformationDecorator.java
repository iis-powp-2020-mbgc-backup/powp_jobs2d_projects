package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

public class Jobs2dDriverTransformationDecorator implements Job2dDriver{
    protected Job2dDriver driver;
    private int xTrans=1;
    private int yTrans=1;
    public Jobs2dDriverTransformationDecorator(Job2dDriver driver, int xTrans, int yTrans) {
        this.driver = driver;
        this.xTrans *= xTrans;
        this.yTrans *= yTrans;
    }

    @Override
    public void setPosition(int x, int y) {
        driver.setPosition(x*xTrans, y*yTrans);
    }

    @Override
    public void operateTo(int x, int y) {
        driver.operateTo(x*xTrans, y*yTrans);
    }
}
