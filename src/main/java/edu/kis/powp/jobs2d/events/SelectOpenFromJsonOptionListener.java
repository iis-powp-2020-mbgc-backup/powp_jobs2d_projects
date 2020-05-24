package edu.kis.powp.jobs2d.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.InterfaceAdapter;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class SelectOpenFromJsonOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            System.out.println(f.getName());
            drawFromJson(f.getPath());
        }
    }

    private void drawFromJson(String fileName) {
        List<DriverCommand> commands;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DriverCommand.class, new InterfaceAdapter());
        Gson gson = builder.create();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        assert reader != null;
        DriverCommand[] carJsonArray = gson.fromJson(reader, DriverCommand[].class);
        for(DriverCommand aCar : carJsonArray){
            System.out.println(aCar.getClass());
        }

        commands = Arrays.asList(carJsonArray);
        manager.setCurrentCommand(commands, fileName);
    }
}