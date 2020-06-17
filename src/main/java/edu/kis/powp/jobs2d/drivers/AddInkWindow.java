package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddInkWindow extends JFrame implements WindowComponent
{
    Job2dDriver driver;
    public AddInkWindow()
    {
        this.setTitle("Add Ink");
        this.setSize(250, 200);
        this.driver = DriverFeature.getDriverManager().getCurrentDriver();

        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        JButton btnClearCommand = new JButton("Refuel Ink");
        btnClearCommand.addActionListener((ActionEvent e) -> this.AddInk());
        content.add(btnClearCommand);
        HideIfVisibleAndShowIfHidden();
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

    private void AddInk(){
        InkUsageDriverAdapter cd = (InkUsageDriverAdapter) driver;
        cd.setBool();
        cd.restoreInk(10000f);
        HideIfVisibleAndShowIfHidden();
    }
}