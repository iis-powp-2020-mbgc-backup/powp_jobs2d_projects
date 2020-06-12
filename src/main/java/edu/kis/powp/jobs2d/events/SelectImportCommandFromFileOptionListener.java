package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.LoadFromFile;
import org.apache.commons.io.FilenameUtils;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SelectImportCommandFromFileOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) throws IllegalArgumentException{
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            String command;
            try {
                command = new String(Files.readAllBytes(Paths.get(f.getPath())));
                LoadFromFile loadFromFile = new LoadFromFile();
                loadFromFile.loadBaseOnExtension(command, FilenameUtils.getExtension(f.getPath()));
            } catch (IOException ex1) {
                throw new IllegalArgumentException();
            }
        }
    }
}