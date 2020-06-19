package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.InkUsageDriverDecorator;
import edu.kis.powp.jobs2d.features.DriverFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RefuelInkWindow extends JFrame implements WindowComponent
{
    InkUsageDriverDecorator driver;
    public RefuelInkWindow(InkUsageDriverDecorator driver)
    {
        this.setTitle("Refuel Ink Window");
        this.setSize(250, 200);
        this.driver = driver;

        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        JButton btnClearCommand = new JButton("Refuel Ink");
        btnClearCommand.setBounds(100,100,140, 40);

        JLabel label = new JLabel();
        label.setText("Amount of ink to fill: ");
        label.setBounds(10, 10, 100, 100);
        content.add(label);

        JTextField textfield= new JTextField();
        textfield.setBounds(110, 50, 130, 30);
        content.add(textfield);

        btnClearCommand.addActionListener((ActionEvent e) -> this.RefuelInk(textfield.getText()));
        content.add(btnClearCommand);
        HideIfVisibleAndShowIfHidden();
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }

    private void RefuelInk(String amount){
        try{
            int inkAmount = Integer.parseInt(amount);
            this.driver.restoreInk(inkAmount);

        }catch(Exception e){
            this.driver.restoreInk(10000f);
        }

        this.driver.setIsAvailable();
        HideIfVisibleAndShowIfHidden();
    }
}