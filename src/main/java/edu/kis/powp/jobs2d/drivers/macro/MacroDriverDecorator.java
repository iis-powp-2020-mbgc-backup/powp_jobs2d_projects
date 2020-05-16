package edu.kis.powp.jobs2d.drivers.macro;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;


public class MacroDriverDecorator implements Job2dDriver {
    private List<DriverCommand> driverCommandList;

    private Job2dDriver driver;

    public MacroDriverDecorator() {
        this.driverCommandList = new ArrayList<>();
    }

    public MacroDriverDecorator(List<DriverCommand> driverCommandList) {
        this.driverCommandList = driverCommandList;
    }

    public void setDriver(Job2dDriver driver) {
        this.driver = driver;
    }

    public void clearCommandSet() {
        driverCommandList = new ArrayList<>();
    }

    public List<DriverCommand> getDriverCommandList() {
        return driverCommandList;
    }

    public Job2dDriver getDriver() {
        return driver;
    }

    @Override
    public void setPosition(int x, int y) {
        driverCommandList.add(new SetPositionCommand(x, y));
    }

    @Override
    public void operateTo(int x, int y) {
        driverCommandList.add(new OperateToCommand(x, y));
    }

    @Override
    public String toString() {
        return "MacroDriverAdapter";
    }
}
