package edu.kis.powp.jobs2d.features.Transformations;

public class TransformationDecorator implements Transformation{
    private Transformation transformation;
    final String SCALE2 = "scale 2x", SCALE05 = "scale 0.5x",
            ROTATE30 = "rotate 30", FLIPH = "flip horizontal",FLIPV = "flip vertical";


    public TransformationDecorator(String transformationName){
        switch (transformationName){
            case SCALE2:
                transformation = new ScaleTransformation(2);
                break;
            case SCALE05:
                transformation = new ScaleTransformation(0.5);
                break;
            case ROTATE30:
                transformation = new RotateTransformation(30);
                break;
            case FLIPH:
                transformation = new FlipTransformation(false);
                break;
            case FLIPV:
                transformation = new FlipTransformation(true);
                break;
        }
    }


    @Override
    public int transformX(int x, int y) {
        return transformation.transformX(x, y);
    }

    @Override
    public int transformY(int x, int y) {
        return transformation.transformY(x, y);
    }
}
