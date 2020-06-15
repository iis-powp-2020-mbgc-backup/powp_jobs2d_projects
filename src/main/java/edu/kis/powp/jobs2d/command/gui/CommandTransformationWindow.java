package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.CommandCoordinatesVisitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.transformations.FlipHorizontal;
import edu.kis.powp.jobs2d.command.transformations.FlipVertical;
import edu.kis.powp.jobs2d.command.transformations.Move;
import edu.kis.powp.jobs2d.command.transformations.Rotation;
import edu.kis.powp.jobs2d.command.transformations.Scaling;
import edu.kis.powp.jobs2d.command.transformations.ShearX;
import edu.kis.powp.jobs2d.command.transformations.ShearY;
import edu.kis.powp.jobs2d.command.transformations.Transformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

public class CommandTransformationWindow extends JFrame implements WindowComponent {
	
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private DriverCommandManager commandManager;
    private JTextArea shiftXValueField;
    private JTextArea shiftYValueField;
    private JTextArea scaleValueField;
    private JTextArea angleValueField;
    private JTextArea shearXValueField;
    private JTextArea shearYValueField;

    public CommandTransformationWindow(DriverCommandManager commandManager) {
        this.setTitle("Command Transformation Manager");
        this.setSize(400, 600);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();

        shiftXValueField = new JTextArea("Enter X here");
        shiftXValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(shiftXValueField, c);

        shiftYValueField = new JTextArea("Enter Y here");
        shiftYValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(shiftYValueField, c);

        JButton btnLoadTransformCommand = new JButton("Transform command");
        btnLoadTransformCommand.addActionListener((ActionEvent e) -> this.transform(new Move(getFieldValue(shiftXValueField.getText()), getFieldValue(shiftYValueField.getText()))));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadTransformCommand, c);

        scaleValueField = new JTextArea("Enter scale value");
        scaleValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(scaleValueField, c);

        JButton btnLoadScaleCommand = new JButton("Scale command");
        btnLoadScaleCommand.addActionListener((ActionEvent e) -> this.transform(new Scaling(getFieldValue(scaleValueField.getText()))));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadScaleCommand, c);

        angleValueField = new JTextArea("Enter angle");
        angleValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(angleValueField, c);

        JButton btnLoadRotateCommand = new JButton("Rotate command");
        btnLoadRotateCommand.addActionListener((ActionEvent e) -> this.transform(new Rotation(getFieldValue(angleValueField.getText()))));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadRotateCommand, c);

        shearXValueField = new JTextArea("Enter Shear X value");
        shearXValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(shearXValueField, c);

        JButton btnLoadShearXCommand = new JButton("Shear X command");
        btnLoadShearXCommand.addActionListener((ActionEvent e) -> this.transform(new ShearX(getFieldValue(shearXValueField.getText()))));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadShearXCommand, c);

        shearYValueField = new JTextArea("Enter Shear Y value");
        shearYValueField.setEditable(true);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(shearYValueField, c);

        JButton btnLoadShearYCommand = new JButton("Shear Y command");
        btnLoadShearYCommand.addActionListener((ActionEvent e) -> this.transform(new ShearY(getFieldValue(shearYValueField.getText()))));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadShearYCommand, c);


        JButton btnLoadhorizontalCommand = new JButton("Flip horizontal command");
        btnLoadhorizontalCommand.addActionListener((ActionEvent e) -> this.transform(new FlipHorizontal()));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadhorizontalCommand, c);

        JButton btnLoadVerticalCommand = new JButton("Flip vertical command");
        btnLoadVerticalCommand.addActionListener((ActionEvent e) -> this.transform(new FlipVertical()));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnLoadVerticalCommand, c);

    }
    
    private void transform(Transformation transformation) {
    	CommandCoordinatesVisitor visitor = new CommandCoordinatesVisitor();
        visitor.visit(castToICompoundCommand(commandManager.getCurrentCommand()));
        DriverCommand driverCommand = transformation.transform(visitor);
        commandManager.setCurrentCommand(driverCommand);
    }
    
    private Integer getFieldValue(String input) {
        Integer result = null;
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException nfe) {
            System.out.println("invalid input");
        }
        return result;
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
    
    private ICompoundCommand castToICompoundCommand(DriverCommand driverCommand) {
    	if(driverCommand instanceof ICompoundCommand) {
    		return (ICompoundCommand) driverCommand;
    	}
    	else {
    		logger.info("Command cannot be transformed");
    		return null;
    	}
    }
}
