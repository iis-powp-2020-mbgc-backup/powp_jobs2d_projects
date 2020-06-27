package edu.kis.powp.jobs2d.drivers.macro;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.util.ArrayList;
import java.util.List;


public class MacroAdapter implements Job2dDriver {
    private List<DriverCommand> driverCommandList;

    private Job2dDriver driver;

    public MacroAdapter() {
        this.driverCommandList = new ArrayList<>();
    }

    public MacroAdapter(List<DriverCommand> driverCommandList) {
        this.driverCommandList = driverCommandList;
    }

    public void setDriver(Job2dDriver driver) {
        this.driver = driver;
    }
    public Job2dDriver getDriver() {
        return driver;
    }

    public void clearCommandSet() {
        driverCommandList = new ArrayList<>();
    }

    public List<DriverCommand> getDriverCommandList() {
        return driverCommandList;
    }

    public void setDriverFeatureCurrentDriver() {
        DriverFeature.getDriverManager().setCurrentDriver(driver);
    }

    @Override
    public void setPosition(int i, int i1) {
        driverCommandList.add(new SetPositionCommand(i, i1));
    }

    @Override
    public void operateTo(int i, int i1) {
        driverCommandList.add(new OperateToCommand(i, i1));
    }

    @Override
    public String toString() {
        return "MacroAdapter";
    }
}