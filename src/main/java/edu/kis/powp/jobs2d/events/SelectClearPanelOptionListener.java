package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.jobs2d.features.DrawerFeature;

import javax.swing.*;

public class SelectClearPanelOptionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		DrawerFeature.getDrawerController().clearPanel();
		MouseListener[] listeners = DrawerFeature.getPanel().getMouseListeners();
		for (MouseListener listener: listeners) {
			DrawerFeature.getPanel().removeMouseListener(listener);
		}
	}
}
