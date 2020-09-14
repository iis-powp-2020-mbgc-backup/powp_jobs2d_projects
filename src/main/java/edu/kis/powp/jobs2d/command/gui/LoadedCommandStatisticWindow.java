package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorCounter;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class LoadedCommandStatisticWindow extends JFrame implements WindowComponent {

    private JFrame frame;
    private JTextArea textArea;
    private DriverCommandManager driverCommandManager;
    private DriverCommand driverCommand;


    public LoadedCommandStatisticWindow(DriverCommandManager driverCommandManager) {
        this.driverCommandManager = driverCommandManager;
        frame = new JFrame("Statistic");
        Dimension screenSize = new Dimension(450, 220);
        frame.setPreferredSize(screenSize);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);

        textArea = new JTextArea("No command loaded");
        textArea.setEditable(false);
        frame.add(textArea);

    }

    public void updateTextArea() {
        driverCommand = driverCommandManager.getCurrentCommand();

        CommandVisitorCounter commandVisitorCounter = new CommandVisitorCounter();
        CommandsFeature.getDriverCommandManager().getCurrentCommand().accept(commandVisitorCounter);

        String currentCommand = driverCommandManager.getCurrentCommandString();
        int operateToCounter = commandVisitorCounter.getOperateToCounter();
        int setPositionCounter = commandVisitorCounter.getSetPositionCounter();
        int numberOfSubcommands = operateToCounter + setPositionCounter;
        double operationalDistance = commandVisitorCounter.getOperationalDistance();
        double nonOperationalDistance = commandVisitorCounter.getNonOperationalDistance();
        double timeOfExecution = operationalDistance / 400 + nonOperationalDistance / 3000;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);


        textArea.setText("Current command:\n" + currentCommand + "\n"
                + "Number of Sub-commands: " + numberOfSubcommands + "\n"
                + "Number of operateTo command: " + operateToCounter + "\n"
                + "Number of setPosition command: " + setPositionCounter + "\n"
                + "Operational distance: " + df.format(operationalDistance) + "mm" + "\n"
                + "Non Operational distance: " + df.format(nonOperationalDistance) + "mm" + "\n"
                + "Operational speed: 400mm/min\n"
                + "Non operational speed: 3000mm/min\n"
                + "Time of execution: " + (int) timeOfExecution + "min " + (int) (timeOfExecution % (int) timeOfExecution * 0.6 * 100) + "seconds"
        );
    }

    public void changeWindowVisibility() {
        frame.setVisible(true);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }
}
