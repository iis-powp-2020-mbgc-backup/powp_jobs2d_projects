package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverComposite implements Job2dDriver {

    private List<Job2dDriver> job2dDriverList;

    public DriverComposite() {
        job2dDriverList = new ArrayList<>();
    }

    public DriverComposite(List driverList) {
        this.job2dDriverList = driverList;
    }

    public void addDriver(Job2dDriver job2dDriver) {
        job2dDriverList.add(job2dDriver);
    }

    public List<Job2dDriver> getDrivers(){
        return job2dDriverList;
    }

    @Override
    public void setPosition(int x, int y) {
        for (Job2dDriver job2dDriver : job2dDriverList) {
            job2dDriver.setPosition(x, y);
        }
    }

    @Override
    public void operateTo(int x, int y) {
        for (Job2dDriver job2dDriver : job2dDriverList) {
            job2dDriver.operateTo(x, y);
        }
    }

    public List<Job2dDriver> getJob2dDriverList() {
        return job2dDriverList;
    }
}
