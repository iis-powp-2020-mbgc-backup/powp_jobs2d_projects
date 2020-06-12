package edu.kis.powp.jobs2d.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import edu.kis.powp.jobs2d.command.manager.DriverCommandAdapter;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class LoadFromJson implements CommandReader {
    @Override
    public List<DriverCommand> load(String command) {
        List<DriverCommand> commands;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DriverCommand.class, new DriverCommandAdapter());
        Gson gson = builder.create();
        JsonReader reader = new JsonReader(new StringReader(command));
        DriverCommand[] driverCommands = gson.fromJson(reader, DriverCommand[].class);
        commands = Arrays.asList(driverCommands);
        return commands;
    }
}
