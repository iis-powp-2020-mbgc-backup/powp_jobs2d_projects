package edu.kis.powp.jobs2d.command.transformation;

public class CommandVisitorTransformationFlip extends CommandVisitorTransformation {

    private final boolean isHorizontal;

    public CommandVisitorTransformationFlip(boolean isHorizontal) {
        super();
        this.isHorizontal = isHorizontal;
    }

    @Override
    int transformXPoint(int x, int y) {
        return isHorizontal ? -x : x;
    }

    @Override
    int transformYPoint(int x, int y) {
        return isHorizontal ? y : -y;
    }
}
