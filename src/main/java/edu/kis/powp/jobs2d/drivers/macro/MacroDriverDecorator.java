package edu.kis.powp.jobs2d.drivers.macro;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.util.ArrayList;
import java.util.List;


public class MacroDriverDecorator implements Job2dDriver {
    private List<DriverCommand> driverCommandList;
    private Job2dDriver driver;

    public MacroDriverDecorator() {
        this.driverCommandList = new ArrayList<>();
    }

    public void setDriver() {
        this.driver = DriverFeature.getDriverManager().getCurrentDriver();
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


    @Override
    public void setPosition(int i, int i1) {
        driver.setPosition(i,i1);
        driverCommandList.add(new SetPositionCommand(i, i1));
    }

    @Override
    public void operateTo(int i, int i1) {
        driver.operateTo(i,i1);
        driverCommandList.add(new OperateToCommand(i, i1));
    }

    @Override
    public String toString() {
        return "MacroDriverDecorator";
    }
}