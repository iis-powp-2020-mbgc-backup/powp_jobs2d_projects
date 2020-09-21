package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.observer.Subscriber;

public class CommandEditorWindowCommandChangeObserver implements Subscriber {
    private CommandEditorWindow commandEditorWindow;

    public CommandEditorWindowCommandChangeObserver(CommandEditorWindow commandEditorWindow) {
        super();
        this.commandEditorWindow = commandEditorWindow;
    }

    public String toString() {
        return "Current command change observer for command manager window";
    }

    @Override
    public void update() {
        commandEditorWindow.updateCurrentCommandField();
        commandEditorWindow.updateCurrentCommandPartList();
    }
}