package edu.kis.powp.jobs2d.command.manager.parsers;

import com.google.gson.Gson;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class JSONCommandParser implements Parser {
    private static class DriverCommandAdapterJSON {
        String name;
        static class Command {
            String operation;
            int posX;
            int posY;
        }
        List<Command> commands;
    }

    private DriverCommandAdapterJSON complexCommandFromJSON;
    private String json;

    public JSONCommandParser(String json) {
        this.json = json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public void parseToDriverCommand() {
        Gson gson = new Gson();

        complexCommandFromJSON =
                gson.fromJson(json, DriverCommandAdapterJSON.class);
    }

    public List<DriverCommand> getDriverCommand() {
        List<DriverCommand> complexCommand = new ArrayList<>();

        complexCommandFromJSON.commands.forEach(command -> {
            if(command.operation.equals("SetPositionCommand")){
                complexCommand.add(new SetPositionCommand(command.posX, command.posY));
            } else if (command.operation.equals("OperateToCommand")) {
                complexCommand.add(new OperateToCommand(command.posX, command.posY));
            }
        });

        return complexCommand;
    }

    public String getDriverCommandName() {
        return complexCommandFromJSON.name;
    }
}
