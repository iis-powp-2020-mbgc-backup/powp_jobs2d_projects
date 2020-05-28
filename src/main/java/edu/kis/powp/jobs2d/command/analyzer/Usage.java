package edu.kis.powp.jobs2d.command.analyzer;

/**
 * struct for computations
 */
public class Usage {
    private UsageType type;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Usage(UsageType type, int startX, int startY, int endX, int endY) {
        this.type = type;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public UsageType getType() {
        return type;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
