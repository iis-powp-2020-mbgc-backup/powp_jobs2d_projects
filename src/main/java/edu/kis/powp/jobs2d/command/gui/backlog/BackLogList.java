package edu.kis.powp.jobs2d.command.gui.backlog;

import edu.kis.powp.jobs2d.command.DriverCommand;

import javax.swing.*;
import java.time.format.DateTimeFormatter;

public class BackLogList {
	private static DefaultListModel<BackLogItem> backlogCommandList = new DefaultListModel<>();

	public static DefaultListModel<BackLogItem> getBacklogCommandList() {
		return backlogCommandList;
	}

	public static void addCommand(String commandName, DriverCommand command)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		BackLogItem backLogItem = new BackLogItem(java.time.LocalTime.now().format(dtf), commandName, command);
		backlogCommandList.addElement(backLogItem);
	}
}
