package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.observer.Subscriber;

public class CommandEditorWindowCommandChangeObserver implements Subscriber {

    private CommandEditorWindow commandEditorWindow;

    public CommandEditorWindowCommandChangeObserver(CommandEditorWindow commandManagerWindow) {
        super();
        this.commandEditorWindow = commandManagerWindow;
    }

    @Override
    public void update() {
        commandEditorWindow.updateCurrentCommandField();
    }

}
