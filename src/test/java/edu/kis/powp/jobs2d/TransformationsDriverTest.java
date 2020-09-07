package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.drivers.transformation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformationsDriverTest {
    @Test
    void transformations() {
        Point basePoint = new Point(10, 10);

        Rotate rotate = new Rotate(Math.PI/2);
        Point rotateP = rotate.apply(basePoint);
        assertEquals(rotateP.getX(), -10);
        assertEquals(rotateP.getY(), 10);

        Scale scale = new Scale(1, 2);
        Point scaleP = scale.apply(basePoint);
        assertEquals(scaleP.getX(), 10);
        assertEquals(scaleP.getY(), 20);

        Shear shear = new Shear(1, 2);
        Point shearP = shear.apply(basePoint);
        assertEquals(shearP.getX(), 20);
        assertEquals(shearP.getY(), 30);

        Translate translate = new Translate(1, 2);
        Point translateP = translate.apply(basePoint);
        assertEquals(translateP.getX(), 11);
        assertEquals(translateP.getY(), 12);

    }
}