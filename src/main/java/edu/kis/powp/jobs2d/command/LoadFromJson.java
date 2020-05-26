package edu.kis.powp.jobs2d.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import edu.kis.powp.jobs2d.command.manager.DriverCommandAdapter;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class LoadFromJson implements Loader {
    @Override
    public void load(String command) {
        List<DriverCommand> commands;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DriverCommand.class, new DriverCommandAdapter());
        Gson gson = builder.create();
        JsonReader reader = new JsonReader(new StringReader(command));
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        DriverCommand[] driverCommands = gson.fromJson(reader, DriverCommand[].class);

        commands = Arrays.asList(driverCommands);
        manager.setCurrentCommand(commands, "LoadFromJson");
    }
}
