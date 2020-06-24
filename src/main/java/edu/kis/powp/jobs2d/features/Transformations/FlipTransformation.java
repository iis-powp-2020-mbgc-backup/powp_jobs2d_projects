package edu.kis.powp.jobs2d.features.Transformations;

public class FlipTransformation implements Transformation{
    private final boolean vertically;

    public FlipTransformation(boolean vertically) {
        this.vertically = vertically;
    }

    @Override
    public int transformX(int x, int y) {
        return vertically ? -x : x;
    }

    @Override
    public int transformY(int x, int y) {
        return vertically ? y : -y;
    }
}
