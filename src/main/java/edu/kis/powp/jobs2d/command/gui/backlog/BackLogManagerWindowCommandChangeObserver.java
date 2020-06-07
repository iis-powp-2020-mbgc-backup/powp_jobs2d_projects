package edu.kis.powp.jobs2d.command.gui.backlog;
import edu.kis.powp.observer.Subscriber;

public class BackLogManagerWindowCommandChangeObserver implements Subscriber {

    private BackLogManagerWindow backLogManagerWindow;

    public BackLogManagerWindowCommandChangeObserver(BackLogManagerWindow commandManagerWindow) {
        super();
        this.backLogManagerWindow = commandManagerWindow;
    }

    @Override
    public void update() {
        backLogManagerWindow.updateBackLogCommandList();
    }
}
