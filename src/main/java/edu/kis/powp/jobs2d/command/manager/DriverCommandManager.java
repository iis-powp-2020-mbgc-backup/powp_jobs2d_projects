package edu.kis.powp.jobs2d.command.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.observer.Publisher;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Driver command Manager.
 */
public class DriverCommandManager {
    private DriverCommand currentCommand = null;

    private Publisher changePublisher = new Publisher();

    /**
     * Set current command.
     *
     * @param commandList Set the command as current.
     */
    public synchronized void setCurrentCommand(DriverCommand commandList) {
        this.currentCommand = commandList;
        changePublisher.notifyObservers();
    }

    /**
     * Set current command.
     *
     * @param commandList list of commands representing a compound command.
     * @param name        name of the command.
     */
    public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
        setCurrentCommand(new ICompoundCommand() {

            List<DriverCommand> driverCommands = commandList;

            @Override
            public void execute(Job2dDriver driver) {
                driverCommands.forEach((c) -> c.execute(driver));
            }

            @Override
            public Iterator<DriverCommand> iterator() {
                return driverCommands.iterator();
            }

            @Override
            public String toString() {
                return name;
            }
        });

    }

    /**
     * Return current command.
     *
     * @return Current command.
     */
    public synchronized DriverCommand getCurrentCommand() {
        return currentCommand;
    }

    public synchronized void clearCurrentCommand() {
        currentCommand = null;
    }

    public synchronized String getCurrentCommandString() {
        if (getCurrentCommand() == null) {
            return "No command loaded";
        } else
            return getCurrentCommand().toString();
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }

    class Command {
        String operation;
        int posX;
        int posY;
    }

    public synchronized void loadCommands(String loadedCommands) {
        Gson gson = new Gson();
        Type dataListType = new TypeToken<Collection<Command>>() {
        }.getType();
        List<Command> all = gson.fromJson(loadedCommands, dataListType);
        List<DriverCommand> po = new ArrayList<DriverCommand>();
        for (Command t : all) {
            if (t.operation.equals("SetPositionCommand")) {
                po.add(new SetPositionCommand(t.posX, t.posY));
            } else if (t.operation.equals("OperateToCommand")) {
                po.add(new OperateToCommand(t.posX, t.posY));
            }
        }
        setCurrentCommand(po, "Loaded input");
    }
}
