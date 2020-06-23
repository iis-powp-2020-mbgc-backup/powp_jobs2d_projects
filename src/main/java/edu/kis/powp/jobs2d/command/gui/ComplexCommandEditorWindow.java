package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class ComplexCommandEditorWindow extends JFrame implements WindowComponent {

    private final JLabel commandNameValue;
    private final CommandManager commandManager;
    private final JTextField paramXInput;
    private final JTextField paramYInput;

    public ComplexCommandEditorWindow(CommandManager commandManager) {
        this.commandManager = commandManager;

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
        mainRightPanel.setLayout(new BoxLayout(mainRightPanel, BoxLayout.Y_AXIS));
        mainPanel.add(mainRightPanel);

        JPanel mainRightParametersPanel = new JPanel();
        mainRightPanel.add(mainRightParametersPanel);

        JPanel mainRightOrderPanel = new JPanel();
        mainRightPanel.add(mainRightOrderPanel);

        JPanel mainRightBottomPanel = new JPanel();
        mainRightBottomPanel.setBackground(Color.PINK);
        mainRightPanel.add(mainRightBottomPanel);

        // Controls
        JLabel commandName = new JLabel("Current command: ");
        topNamePanel.add(commandName);
        commandNameValue = new JLabel("no command loaded");
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
        commandList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainLeftPanel.setViewportView(commandList);
        commandList.addListSelectionListener(this::handleListSelectionEvent);

        JLabel paramXLabel = new JLabel("X: ");
        mainRightParametersPanel.add(paramXLabel);
        paramXInput = new JTextField(5);
        mainRightParametersPanel.add(paramXInput);

        JLabel paramYLabel = new JLabel("Y: ");
        mainRightParametersPanel.add(paramYLabel);
        paramYInput = new JTextField(5);
        mainRightParametersPanel.add(paramYInput);

        JButton changeOrderUpButton = new JButton("Up");
        mainRightOrderPanel.add(changeOrderUpButton);

        JButton changeOrderDownButton = new JButton("Down");
        mainRightOrderPanel.add(changeOrderDownButton);

        JButton confirmButton = new JButton("Apply changes");
        mainRightBottomPanel.add(confirmButton);

        content.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void handleListSelectionEvent(ListSelectionEvent e) {
        JList<String> list = (JList<String>)e.getSource();
        if(!e.getValueIsAdjusting()) {
            paramXInput.setText(String.valueOf(list.getSelectedIndex()));
        }
    }

    public void updateCurrentCommand() {
        commandNameValue.setText(commandManager.getCurrentCommandString());
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
