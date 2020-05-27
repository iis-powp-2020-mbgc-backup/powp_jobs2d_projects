package edu.kis.powp.jobs2d.command.manager;

import com.google.gson.Gson;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

class JSONCommandParser {
    private DriverCommandManager.ComplexCommand complexCommandFromJSON;

    public JSONCommandParser(String json) {
        Gson gson = new Gson();

        complexCommandFromJSON =
                gson.fromJson(json, DriverCommandManager.ComplexCommand.class);
    }

    public List<DriverCommand> getComplexCommand() {
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

    public String getComplexCommandName() {
        return complexCommandFromJSON.name;
    }
}
