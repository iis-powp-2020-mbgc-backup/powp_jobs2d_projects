package edu.kis.powp.jobs2d.events;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.ScaledLineDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.transformations.CoordsFlipTransformation;
import edu.kis.powp.jobs2d.drivers.transformations.CoordsTransformation;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectDriverTransformation implements ActionListener
{
	CoordsTransformation transformation;

	public SelectDriverTransformation(CoordsTransformation transformation)
	{
		this.transformation = transformation;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Job2dDriver driver = null;
		if (DriverFeature.getDriverManager().getCurrentDriver() instanceof LineDriverAdapter)
		{
			LineDriverAdapter temp = (LineDriverAdapter)DriverFeature.getDriverManager().getCurrentDriver();
			driver = new ScaledLineDriver(temp, transformation);
		}

		DriverFeature.getDriverManager().setCurrentDriver(driver);
	}
}
