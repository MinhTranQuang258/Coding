package com.spsvn.studyspringcore;

import org.springframework.context.ApplicationEvent;

public class Point extends ApplicationEvent{
	
	private int x= 5;
	
	private int y= 5;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point(Object source) {
		super(source);
	}

	@Override
	public Object getSource() {
		return super.getSource();
	}
}
