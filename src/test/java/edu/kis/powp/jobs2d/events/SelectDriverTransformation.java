package edu.kis.powp.jobs2d.events;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.Driver2dTransformationsDecorator;
import edu.kis.powp.jobs2d.drivers.transformations.CoordsTransformation;
import edu.kis.powp.jobs2d.features.DriverFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectDriverTransformation implements ActionListener
{
	private CoordsTransformation transformation;

	public SelectDriverTransformation(CoordsTransformation transformation)
	{
		this.transformation = transformation;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Job2dDriver driver = new Driver2dTransformationsDecorator(DriverFeature.getDriverManager().getCurrentDriver(), transformation);

		DriverFeature.getDriverManager().setCurrentDriver(driver);
	}
}
