package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

public class LoadFromFile {
    
    public void loadBaseOnExtension(String command, String fileExtension) {
        CommandReader commandReader;
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        if ("json".equals(fileExtension)) {
            commandReader = new LoadFromJson();
        } else {
            throw new IllegalStateException("Unexpected value: " + fileExtension);
        }
        manager.setCurrentCommand(commandReader.load(command), "LoadFromFile");
    }

}
