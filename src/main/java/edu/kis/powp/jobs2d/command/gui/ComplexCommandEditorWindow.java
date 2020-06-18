package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;

import javax.swing.*;

public class ComplexCommandEditorWindow extends JFrame implements WindowComponent {

    public ComplexCommandEditorWindow() {
        this.setTitle("Complex command editor");
        this.setSize(640, 480);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
