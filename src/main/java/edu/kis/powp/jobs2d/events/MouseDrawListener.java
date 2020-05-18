package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseDrawListener implements MouseListener {

    private JPanel panel;

    public MouseDrawListener(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();

        if (e.getButton() == MouseEvent.BUTTON1) {
            currentDriver.operateTo(e.getX() - panel.getWidth() / 2, e.getY() - panel.getHeight() / 2);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            currentDriver.setPosition(e.getX() - panel.getWidth() / 2, e.getY() - panel.getHeight() / 2);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
