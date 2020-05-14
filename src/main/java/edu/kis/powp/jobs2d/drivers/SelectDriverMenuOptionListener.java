package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class SelectDriverMenuOptionListener extends Observable implements ActionListener {
	private DriverManager driverManager;
	private Job2dDriver driver = null;
	public static SelectDriverMenuOptionListener instance;
	DriverFeature df;

	public SelectDriverMenuOptionListener(Job2dDriver driver, DriverManager driverManager) {
		this.driverManager = driverManager;
		this.driver = driver;
		this.instance = this;
		this.df = new DriverFeature();
		addObserver(new DriverFeature());
		notifyObservers();

	}

	public static SelectDriverMenuOptionListener getInstance(){
		return instance;
	}

	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
	}

	@Override
	public synchronized void deleteObserver(Observer o) {
		super.deleteObserver(o);
	}

	@Override
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}


	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}

	@Override
	public synchronized boolean hasChanged() {
		return true;
	}

	@Override
	public synchronized void deleteObservers() {
		super.deleteObservers();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		driverManager.setCurrentDriver(driver);
		notifyObservers("qwe");
		//DriverFeature.updateDriverInfo();
	}
}
