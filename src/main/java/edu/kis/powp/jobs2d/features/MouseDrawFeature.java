package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDrawFeature {

    public static void SetMouseListener(Application application) {
        application.getFreePanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                System.out.println("X: " + x + ",  Y: " + y);
                Job2dDriver driver = DriverFeature.getDriverManager().getCurrentDriver();
                driver.operateTo(x, y);
                driver.setPosition(x, y);
            }
        });
    }
}
