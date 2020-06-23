package edu.kis.powp.jobs2d.features.Transformations;

public class ScaleTransformation implements Transformation {
    private double scale;

    public ScaleTransformation(double scale){
        this.scale = scale;
    }


    @Override
    public int transformX(int x, int y) {
        return (int) (x*scale);
    }

    @Override
    public int transformY(int x, int y) {
        return (int) (y*scale);
    }
}
