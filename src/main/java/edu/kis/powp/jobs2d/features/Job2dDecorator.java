package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;

public abstract class Job2dDecorator implements Job2dDriver {

    protected Job2dDriver job2dDriver;

    public Job2dDecorator(Job2dDriver job2dDriver) {
        this.job2dDriver = job2dDriver;
    }

    @Override
    public void setPosition(int x, int y) {
        this.job2dDriver.setPosition(x, y);
    }

    @Override
    public void operateTo(int x, int y) {
        this.job2dDriver.operateTo(x, y);
    }
}
