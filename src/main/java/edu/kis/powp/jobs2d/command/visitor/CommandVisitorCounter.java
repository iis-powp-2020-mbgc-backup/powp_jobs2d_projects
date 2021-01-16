package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class CommandVisitorCounter implements CommandVisitorInterface {

    private int operateToCounter = 0;
    private int setPositionCounter = 0;
    private double nonOperationalDistance = 0;
    private double operationalDistance = 0;

    private double lastX = 0;
    private double lastY = 0;

    @Override
    public void visit(OperateToCommand operateToCommand) {
        operationalDistance += calculateDistance(operateToCommand.getPosX(), operateToCommand.getPosY());

        lastX = operateToCommand.getPosX();
        lastY = operateToCommand.getPosY();
        operateToCounter++;
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        nonOperationalDistance += calculateDistance(setPositionCommand.getPosX(), setPositionCommand.getPosY());

        lastX = setPositionCommand.getPosX();
        lastY = setPositionCommand.getPosY();
        setPositionCounter++;
    }

    private double calculateDistance(int x, int y) {
        return sqrt(pow(lastX - x, 2) + pow(lastY - y, 2));
    }

    @Override
    public void visit(ICompoundCommand iCompoundCommand) {
        for (DriverCommand partOfCompoundCommand : iCompoundCommand) {
            partOfCompoundCommand.accept(this);
        }
    }

    public int getOperateToCounter() {
        return operateToCounter;
    }

    public int getSetPositionCounter() {
        return setPositionCounter;
    }

    public double getNonOperationalDistance() {
        return nonOperationalDistance;
    }

    public double getOperationalDistance() {
        return operationalDistance;
    }
}
