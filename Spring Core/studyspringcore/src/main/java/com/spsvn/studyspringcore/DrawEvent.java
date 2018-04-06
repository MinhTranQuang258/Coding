package com.spsvn.studyspringcore;

import org.springframework.context.ApplicationEvent;


public class DrawEvent extends ApplicationEvent{
	
//	private static final long serialVersionUID = 6973014356268900607L;

	public DrawEvent(Object source) {
		super(source);
	}

	@Override
	public String toString() {
		return "Draw event occurred";
	}
	
}
