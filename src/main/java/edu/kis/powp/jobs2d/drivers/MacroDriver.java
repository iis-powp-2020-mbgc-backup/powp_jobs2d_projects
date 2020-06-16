package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;
/**
 * Provides functionality to record executed commands.
 */
public class MacroDriver implements Job2dDriver {
    private List<DriverCommand> driverCommandList = new ArrayList<>();
    private DriverComposite driverComposite;

    /**
     * Adds passed parameters to list as a new SetPositionCommand object and run setPosition method on DriverComposite.
     * @param x coordinate
     * @param y coordinate
     */
    @Override
    public void setPosition(int x, int y) {
        driverCommandList.add(new SetPositionCommand(x, y));
        driverComposite.setPosition(x,y);
    }

    /**
     * Adds passed parameters to list as a new OperateToCommand object and run operateTo method on DriverComposite.
     * @param x
     * @param y
     */
    @Override
    public void operateTo(int x, int y) {
        driverCommandList.add(new OperateToCommand(x, y));
        driverComposite.operateTo(x,y);
    }

    /**
     * @return driverComposite
     */
    public DriverComposite getDriverComposite() {
        return driverComposite;
    }

    /**
     * Sets DriverComposite object.
     * @param driverComposite
     */
    public void setDriverComposite(DriverComposite driverComposite) {
        this.driverComposite = driverComposite;
    }

    /**
     * @return list of DriverCommand objects.
     */
    public List<DriverCommand> getDriverCommandList() {
        return new ArrayList<>(driverCommandList);
    }

    /**
     * Clears currently stored objects of DriverCommand in driverCommandList.
     */
    public void clearMemory() {
        driverCommandList.clear();
    }

    @Override
    public String toString() {
        return "Macro";
    }
}
