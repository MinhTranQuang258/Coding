package com.spsvn.studyspringcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;

@Controller
public class Circle {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public void draw() {
		System.out.println("Circle is Drawn");
		
		DrawEvent drawEvent= new DrawEvent(this);
		
		publisher.publishEvent(drawEvent);
	}
	
	public void publishPoint() {
		Point point= new Point(this);
		
		publisher.publishEvent(point);
	}
}
