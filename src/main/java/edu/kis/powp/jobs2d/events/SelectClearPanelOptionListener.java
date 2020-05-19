package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.features.DrawerFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SelectClearPanelOptionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		DrawerFeature.getDrawerController().clearPanel();
		cleanMouseListeners();
	}

	private void cleanMouseListeners(){
		Arrays.stream(DrawerFeature
				.getPanel()
				.getMouseListeners())
				.forEach(listener -> DrawerFeature
						.getPanel()
						.removeMouseListener(listener));
	}
}
