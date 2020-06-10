package edu.kis.powp.jobs2d.features.Transformations;

public class TransformationDecorator implements Transformation{
    private Transformation transformation;

    public TransformationDecorator(String transformationName){
        switch (transformationName){
            case "scale 2x":
                transformation = new ScaleTransformation(2);
                break;
            case "scale 0.5x":
                transformation = new ScaleTransformation(0.5);
                break;
            case "rotate 30":
                transformation = new RotateTransformation(30);
                break;
            case "flip horizontal":
                transformation = new FlipTransformation(false);
                break;
            case "flip vertical":
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
