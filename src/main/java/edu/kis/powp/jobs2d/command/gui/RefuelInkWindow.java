package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.drivers.InkOperator;
import edu.kis.powp.jobs2d.drivers.InkUsageDriverDecorator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RefuelInkWindow extends JFrame implements WindowComponent
{
    private InkOperator operator;
    public RefuelInkWindow(InkUsageDriverDecorator operator)
    {
        this.setTitle("Refuel Ink Window");
        this.setSize(250, 140);
        this.operator = operator;
        Container content = this.getContentPane();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        this.setResizable(false);
        JButton btnClearCommand = new JButton("Refuel Ink");
        btnClearCommand.setBounds(100,100,140, 40);

        JLabel label1 = new JLabel();
        label1.setText("Currently: "+ String.format("%.2f", operator.getInkLimit())+"/10000units");
        label1.setBounds(10, 10, 10, 20);
        content.add(label1);

        JLabel label2 = new JLabel();
        label2.setText("Amount of ink to fill: ");
        label2.setBounds(10, 20, 10, 10);
        content.add(label2);

        JTextField textField = new JTextField();

        content.add(textField);

        btnClearCommand.addActionListener((ActionEvent e) -> this.RefuelInk(textField.getText()));
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
            operator.restoreInk(inkAmount);


        }catch(Exception e){
            operator.restoreInk(10000f);
        }

        operator.setIsAvailable();
        HideIfVisibleAndShowIfHidden();
    }

}