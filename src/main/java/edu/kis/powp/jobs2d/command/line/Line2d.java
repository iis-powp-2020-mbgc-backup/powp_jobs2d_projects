package edu.kis.powp.jobs2d.command.line;

public class Line2d {
	
	private int startPosX;
	private int startPosY;
	private int endPosX;
	private int endPosY;
	private boolean isOperateTo;

	public boolean getOperateTo() { return isOperateTo; }

	public int getStartPosX() {
		return startPosX;
	}

	public int getStartPosY() {
		return startPosY;
	}

	public int getEndPosX() {
		return endPosX;
	}

	public int getEndPosY() {
		return endPosY;
	}
	
	public void setStartPosX(int startPosX) {
		this.startPosX = startPosX;
	}

	public void setStartPosY(int startPosY) {
		this.startPosY = startPosY;
	}

	public void setEndPosX(int endPosX) {
		this.endPosX = endPosX;
	}

	public void setEndPosY(int endPosY) {
		this.endPosY = endPosY;
	}
	
	public Line2d(int startPosX, int startPosY, int endPosX, int endPosY) {
		this.startPosX = startPosX;
		this.startPosY = startPosY;
		this.endPosX = endPosX;
		this.endPosY = endPosY;
		this.isOperateTo = true;
	}
	
	public Line2d(int startPosX, int startPosY) {
		this.startPosX = startPosX;
		this.startPosY = startPosY;
		this.endPosX = startPosX;
		this.endPosY = startPosY;
		this.isOperateTo = false;
	}
	
}
