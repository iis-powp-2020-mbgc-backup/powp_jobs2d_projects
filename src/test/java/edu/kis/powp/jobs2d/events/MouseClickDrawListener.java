package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.features.DriverFeature;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class used to draw lines on application using mouse clicks:
 * left click to draw line from current head position
 * right click to set new heads position
 */
public class MouseClickDrawListener implements MouseListener {

    private JPanel jPanel;

    public MouseClickDrawListener(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    @Override public void mouseClicked(MouseEvent e) {
        Job2dDriver job2dDriver = DriverFeature.getDriverManager().getCurrentDriver();

        if (e.getButton() == MouseEvent.BUTTON1) {
            new OperateToCommand(e.getX() - jPanel.getWidth() / 2, e.getY() - jPanel.getHeight() / 2).execute(job2dDriver);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            new SetPositionCommand(e.getX() - jPanel.getWidth() / 2, e.getY() - jPanel.getHeight() / 2).execute(job2dDriver);
        }
    }

    @Override public void mousePressed(MouseEvent e) {

    }

    @Override public void mouseReleased(MouseEvent e) {

    }

    @Override public void mouseEntered(MouseEvent e) {

    }

    @Override public void mouseExited(MouseEvent e) {

    }
}
