package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDrawFeature {
    private static final int Width = 268;
    private static final int Height = 226;

    public static void SetMouseListener(Application application) {
        application.getFreePanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int x = mouseEvent.getX() - Width;
                int y = mouseEvent.getY() - Height;
                Job2dDriver driver = DriverFeature.getDriverManager().getCurrentDriver();
                if(SwingUtilities.isLeftMouseButton(mouseEvent))
                {
                    driver.operateTo(x, y);

                }else if(SwingUtilities.isRightMouseButton(mouseEvent))
                {
                    driver.setPosition(x, y);
                }
            }
        });
    }
}
