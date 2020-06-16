package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.InkUsageDriver;
import edu.kis.powp.jobs2d.drivers.InkUsageObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddInkWindow extends JFrame implements WindowComponent
{
    InkUsageDriver driver;
    private AddInkWindow(InkUsageDriver driver)
    {
        this.setTitle("Add Ink");
        this.setSize(250, 200);
        this.driver = driver;

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
        driver.restoreInk(2000f);
        HideIfVisibleAndShowIfHidden();
    }
}