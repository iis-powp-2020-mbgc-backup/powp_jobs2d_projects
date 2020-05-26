package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MonitorUsageWindow extends JFrame implements WindowComponent {
	public static final String USAGEMONITOR_LOGGER = "usagemonitor";
	private JTextArea usageTextAreaViewer;
	
	private static final long serialVersionUID = 9204679248307009948L;
	
	public MonitorUsageWindow(){
		Logger l = Logger.getLogger(USAGEMONITOR_LOGGER);
		
		this.setTitle("Monitor usage");
		this.setSize(400, 300);
		this.usageTextAreaViewer = new JTextArea("");
		this.usageTextAreaViewer.setEditable(false);
		
		l.addHandler(new Handler() {
			@Override
			public void publish(LogRecord logRecord) {
				usageTextAreaViewer.append(logRecord.getMessage());
				usageTextAreaViewer.append("\n");
			}
			
			@Override
			public void flush() {
			
			}
			
			@Override
			public void close() throws SecurityException {
			
			}
		});
		JScrollPane scrollPane = new JScrollPane(this.usageTextAreaViewer);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(scrollPane,  BorderLayout.CENTER);
	}

	public void flush(String driverName) {
		this.setTitle("Monitor usage - " + driverName);
		this.usageTextAreaViewer.setText("");
	}
	
	@Override
	public void HideIfVisibleAndShowIfHidden() {
		this.setVisible(!this.isVisible());
	}
}
