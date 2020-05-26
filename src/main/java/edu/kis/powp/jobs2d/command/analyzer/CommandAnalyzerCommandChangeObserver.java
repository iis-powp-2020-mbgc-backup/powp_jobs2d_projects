package edu.kis.powp.jobs2d.command.analyzer;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

public class CommandAnalyzerCommandChangeObserver implements Subscriber {

    private ICommandUsageAnalyzer analyzer;

    public CommandAnalyzerCommandChangeObserver(ICommandUsageAnalyzer analyzer) {
        super();
        this.analyzer = analyzer;
    }


    @Override
    public void update() {
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if(command instanceof ICompoundCommand){
            analyzer.analyze((ICompoundCommand) command);
        }
    }

}
