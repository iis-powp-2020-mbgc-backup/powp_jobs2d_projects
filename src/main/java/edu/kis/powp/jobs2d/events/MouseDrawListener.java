package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseDrawListener implements MouseListener {

    private JPanel panel;
    private DriverManager manager;

    public MouseDrawListener(JPanel panel, DriverManager manager) {
        this.panel = panel;
        this.manager = manager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Job2dDriver currentDriver = manager.getCurrentDriver();

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
