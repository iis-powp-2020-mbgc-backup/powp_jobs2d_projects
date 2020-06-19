package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ComplexCommandEditorWindow extends JFrame implements WindowComponent {

    public ComplexCommandEditorWindow() {
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        this.setTitle("Complex command editor");
        this.setSize(640, 480);


        // Layout
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(80, 120));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        content.add(topPanel, BorderLayout.PAGE_START);

        JPanel topNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topNamePanel.setBackground(Color.CYAN);
        topPanel.add(topNamePanel);

        JPanel topStatisticsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topStatisticsPanel.setBackground(Color.ORANGE);
        topPanel.add(topStatisticsPanel);

        JPanel topStatisticsNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topStatisticsPanel.add(topStatisticsNumberPanel);

        JPanel topStatisticsLengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topStatisticsPanel.add(topStatisticsLengthPanel);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        content.add(mainPanel, BorderLayout.CENTER);

        JScrollPane mainLeftPanel = new JScrollPane();
        mainLeftPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(mainLeftPanel);

        JPanel mainRightPanel = new JPanel();
        mainRightPanel.setBackground(Color.BLUE);
        mainPanel.add(mainRightPanel);

        // Controls
        JLabel commandName = new JLabel("Current command: ");
        topNamePanel.add(commandName);
        JLabel commandNameValue = new JLabel("[Example command name]");
        topNamePanel.add(commandNameValue);

        JLabel numberOfCommands = new JLabel("Number of commands: ");
        topStatisticsNumberPanel.add(numberOfCommands);
        JLabel numberOfCommandsValue = new JLabel("N/A");
        topStatisticsNumberPanel.add(numberOfCommandsValue);

        JLabel commandLength = new JLabel("Total length: ");
        topStatisticsLengthPanel.add(commandLength);
        JLabel commandLengthValue = new JLabel("N/A");
        topStatisticsLengthPanel.add(commandLengthValue);

        String[] data = {"one", "two", "three", "four", "one", "two", "three", "four", "one", "two", "three", "four"};
        JList<String> commandList = new JList<>(data);
        mainLeftPanel.setViewportView(commandList);

        content.setVisible(true);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
