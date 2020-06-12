package edu.kis.powp.jobs2d.events;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.ScaledLineDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.transformations.DriverLevelFlipTransformation;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectDriverTransformationFlipping implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		DrawPanelController drawerController = DrawerFeature.getDrawerController();
		Job2dDriver driver = null;
		if (DriverFeature.getDriverManager().getCurrentDriver().getClass() == LineDriverAdapter.class)
		{
			LineDriverAdapter temp = (LineDriverAdapter)DriverFeature.getDriverManager().getCurrentDriver();
			driver = new ScaledLineDriver(drawerController, temp.getLine(), "inherited", new DriverLevelFlipTransformation(true));
		}

		DriverFeature.getDriverManager().setCurrentDriver(driver);
	}
}
