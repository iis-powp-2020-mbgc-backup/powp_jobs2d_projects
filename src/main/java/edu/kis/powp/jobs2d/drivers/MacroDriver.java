package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class MacroDriver implements Job2dDriver {

    private DriverComposite driverComposite;
    private Job2dDriver job2dDriver;

    public MacroDriver() {
        driverComposite = new DriverComposite();
    }

    @Override
    public void setPosition(int x, int y) {
        job2dDriver.setPosition(x, y);
        driverComposite.addDriverCommand(new SetPositionCommand(x, y));
    }

    @Override
    public void operateTo(int x, int y) {
        job2dDriver.operateTo(x, y);
        driverComposite.addDriverCommand(new OperateToCommand(x, y));
    }

    @Override
    public String toString() {
        return "Macro";
    }

    public void setCoreJob2dDriver(Job2dDriver job2dDriver) {
        this.job2dDriver = job2dDriver;
    }

    public Job2dDriver getCoreJob2dDriver() {
        return job2dDriver;
    }

    public DriverComposite getDriverComposite() {
        return driverComposite;
    }

    public void clearMemory() {
        driverComposite.clear();
    }
}
