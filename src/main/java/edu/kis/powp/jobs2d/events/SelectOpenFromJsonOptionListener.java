package edu.kis.powp.jobs2d.events;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SelectOpenFromJsonOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DrawPanelController controller = DrawerFeature.getDrawerController();
        //open explorator
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        // optionally set chooser options ...
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            System.out.println(f.getName());
            drawFromJson(f.getPath());
        }
    }

    private void drawFromJson(String fileName) {
        List<DriverCommand> commands = new ArrayList<DriverCommand>();

        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(fileName));
            for (int i = 0; i < jsonObject.size(); i++) {
                JSONObject element = (JSONObject) jsonObject.get(String.valueOf(i + 1));
                String command = (String) element.get("command");
                int x = Integer.parseInt(String.valueOf(element.get("x")));
                int y = Integer.parseInt(String.valueOf(element.get("y")));
                if (command.contains("setPosition"))
                    commands.add(new SetPositionCommand(x, y));
                else if (command.contains("operateTo"))
                    commands.add(new OperateToCommand(x, y));

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        manager.setCurrentCommand(commands, fileName);
    }
}