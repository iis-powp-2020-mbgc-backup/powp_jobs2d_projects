package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

public interface Jobs2dDriver2 extends Job2dDriver {

    void setPosition(int var1, int var2, DriverLevelTransformation transformation);
    void operateTo(int var1, int var2, DriverLevelTransformation transformation);

}
