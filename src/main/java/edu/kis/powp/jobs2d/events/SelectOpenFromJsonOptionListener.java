package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.LoadFromJson;
import edu.kis.powp.jobs2d.command.Loader;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SelectOpenFromJsonOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            String command;
            try {
                command = new String(Files.readAllBytes(Paths.get(f.getPath())));
                Loader loader = new LoadFromJson();
                loader.load(command);
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
        }
    }
}