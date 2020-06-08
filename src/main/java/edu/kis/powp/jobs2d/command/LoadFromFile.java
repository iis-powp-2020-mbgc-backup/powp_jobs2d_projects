package edu.kis.powp.jobs2d.command;

public class LoadFromFile {
    private CommandReader commandReader;
    public void loadBaseOnExtension(String command, String fileExtension) {

        switch(fileExtension) {
            case "json":
                commandReader = new LoadFromJson();
                commandReader.load(command);
                break;
        }
    }

}
