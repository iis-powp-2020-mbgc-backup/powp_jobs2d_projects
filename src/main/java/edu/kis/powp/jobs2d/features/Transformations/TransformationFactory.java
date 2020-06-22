package edu.kis.powp.jobs2d.features.Transformations;

public class TransformationFactory{

    public static Transformation getTransformation(Transformations transformation){
        switch (transformation){
            case SCALE2:
                return new ScaleTransformation(2);
            case SCALE05:
                return new ScaleTransformation(0.5);
            case ROTATE30:
                return new RotateTransformation(30);
            case FLIPV:
                return new FlipTransformation(false);
            case FLIPH:
                return new FlipTransformation(true);
        }

        return null;
    }
}
