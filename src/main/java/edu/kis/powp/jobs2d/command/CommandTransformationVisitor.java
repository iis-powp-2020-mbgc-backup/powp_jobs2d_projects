package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.transformation.PointTransformation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Visitor for command transformations.
 */
public class CommandTransformationVisitor implements CommandVisitorInterface {
    private List<DriverCommand> commands;
    private PointTransformation transformation;

    /**
     * Creates a CommandTransformationVisitor.
     * @param transformation Transformation to perform.
     */
    public CommandTransformationVisitor(PointTransformation transformation) {
        this.transformation = transformation;
        commands = new ArrayList<>();
    }

    /**
     * Get transformed command.
     * @return Transformed command.
     */
    public ICompoundCommand getTransformedCommand() {
        return new CompoundCommand(commands);
    }

    /**
     * Visits OperateToCommand.
     * @param driver Driver to visit.
     */
    @Override
    public void visit(OperateToCommand driver) {
        DriverCommand transformedCommand = new OperateToCommand(transformation.getTransformedX(driver.getPosX(), driver.getPosY()), transformation.getTransformedY(driver.getPosX(), driver.getPosY()));
        commands.add(transformedCommand);
    }

    /**
     * Visits SetPositionCommand.
     * @param driver Driver to visit.
     */
    @Override
    public void visit(SetPositionCommand driver) {
        SetPositionCommand transformedCommand = new SetPositionCommand(transformation.getTransformedX(driver.getPosX(), driver.getPosY()), transformation.getTransformedY(driver.getPosX(), driver.getPosY()));
        commands.add(transformedCommand);
    }

    /**
     * Visits ICompoundCommand.
     * @param driver Driver to visit.
     */
    @Override
    public void visit(ICompoundCommand driver) {
        for (Iterator<DriverCommand> i = driver.iterator(); i.hasNext(); ) {
            i.next().accept(this);
        }
    }
}
