package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.Visitor;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.util.ArrayList;
import java.util.List;

public class DriverComposite implements DriverCommand {

    List<DriverCommand> driverCommandList;

    public DriverComposite() {
        driverCommandList = new ArrayList<>();
    }

    public DriverComposite(List driverCommandList) {
        this.driverCommandList = driverCommandList;
    }

    public void addDriverCommand(DriverCommand driverCommand) {
        driverCommandList.add(driverCommand);
    }

    public List<DriverCommand> getDriverCommandList() {
        return driverCommandList;
    }

    public void clear() {
        driverCommandList.clear();
    }

    @Override
    public void execute(Job2dDriver driver) {
        for (DriverCommand driverCommand : driverCommandList) {
            driverCommand.execute(DriverFeature.getDriverManager().getCurrentDriver());
        }
    }

    @Override
    public DriverCommand clone() throws CloneNotSupportedException {
        return (DriverComposite) super.clone();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
