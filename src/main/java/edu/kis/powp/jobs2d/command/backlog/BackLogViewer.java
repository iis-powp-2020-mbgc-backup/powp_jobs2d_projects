package edu.kis.powp.jobs2d.command.backlog;
import edu.kis.powp.observer.Subscriber;

public class BackLogViewer implements Subscriber {

    private BackLogManager backLogManager;

    public BackLogViewer(BackLogManager commandManagerWindow) {
        super();
        this.backLogManager = commandManagerWindow;
    }

    @Override
    public void update() {
        backLogManager.updateCurrentCommandField();
    }
}
